#!/usr/bin/env bash

set -euo pipefail

cmake_supports_test_dir() {
  ctest --help 2>/dev/null | grep -q -- '--test-dir'
}

default_gxx_major="$(g++ -dumpversion 2>/dev/null | cut -d. -f1 || true)"
cmake_extra_args=()

if [ -x "$(command -v g++-10 || true)" ] && [ -n "$default_gxx_major" ] && [ "$default_gxx_major" -lt 10 ]; then
  cmake_extra_args+=(
    -DCMAKE_C_COMPILER=gcc-10
    -DCMAKE_CXX_COMPILER=g++-10
  )
fi

if ! command -v lcov >/dev/null 2>&1 || ! command -v genhtml >/dev/null 2>&1; then
    echo "lcov and genhtml are required for coverage reporting (sudo apt install lcov)" >&2
    exit 1
fi

get_lcov_2x_inconsistent_param() {
  # lcov 2.0+ introduced 'inconsistent' warning parameter for source/line mismatch issues
  # lcov 1.x uses the older 'mismatch' parameter for backward compatibility
  local lcov_version=$(lcov --version | grep -oP 'lcov: version \K[0-9]+' || echo '1')
  if [ "$lcov_version" -ge 2 ]; then
    echo "inconsistent"
  else
    echo "mismatch"
  fi
}

get_gcov_tool() {
  # gcov要和当初编译用的gcc版本一致。
  # Ubuntu 20.04前文用了gcc-10
  # Ubuntu 24就用默认
  if [ -f /etc/os-release ]; then
    local ubuntu_version=$(grep VERSION_ID /etc/os-release | cut -d'=' -f2 | tr -d '"' | cut -d'.' -f1)
    if [ "$ubuntu_version" = "20" ]; then
      echo "gcov-10"
    else
      echo "gcov"
    fi
  else
    echo "gcov"
  fi
}

for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/CMakeLists.txt" ]; then
        echo "=== $dir ==="
        cd "$dir"

        # # ── Release ──────────────────────────────────────────────
        # echo "  [release] configure & build & test"
        # cmake -S . -B target/release -DCMAKE_BUILD_TYPE=Release -Wno-dev
        # cmake --build target/release
        # ctest --output-on-failure --test-dir target/release

        # # ── Debug ────────────────────────────────────────────────
        # echo "  [debug] configure & build & test"
        # cmake -S . -B target/debug -DCMAKE_BUILD_TYPE=Debug -Wno-dev
        # cmake --build target/debug
        # ctest --output-on-failure --test-dir target/debug

        # ── 测试构建，编译时加入覆盖率埋点（埋点必须嵌入到主程序源码） ─────────────────────────────────────
        echo "  [debug-cov] configure & build & test"
        cmake -S . -B target/debug-cov \
            -DCMAKE_BUILD_TYPE=Debug \
            -DENABLE_COVERAGE=ON \
            "${cmake_extra_args[@]}" \
            -Wno-dev
        cmake --build target/debug-cov

        if cmake_supports_test_dir; then
          ctest --output-on-failure --test-dir target/debug-cov
        else
          #cmake 3.16的时候还不支持--test-dir
          cmake -E chdir target/debug-cov ctest --output-on-failure
        fi
        cat target/debug-cov/*.xml #junit xml report

        # collect coverage from debug-cov build only
        rm -rf target/coverage
        mkdir -p target/coverage

        #提取覆盖率原始数据（运行目标系统时产生的.gcda 文件），转换成lcov格式 
        lcov --capture \
             --directory target/debug-cov \
             --base-directory . \
             --output-file target/coverage/coverage.info \
             --gcov-tool $(get_gcov_tool) \
             --no-external \
             --ignore-errors $(get_lcov_2x_inconsistent_param),source,gcov \
             --quiet

        #排除不属于源代码的覆盖率数据（如第三方库、测试代码、构建输出等）
        lcov --remove target/coverage/coverage.info \
          '*/third_party/*' \
          '*/test/*' \
          '*/target/*' \
          '*/src/catch2/*' \
            --output-file target/coverage/coverage_filtered.info \
          --quiet \
          --ignore-errors unused,unused          
        #与源码一起，组装成html格式的报告
        genhtml target/coverage/coverage_filtered.info \
              --output-directory target/coverage \
            --title "$dir Coverage" \
            --quiet

        echo "  Coverage HTML : $dir/target/coverage/index.html"
        cd ..
    fi
done