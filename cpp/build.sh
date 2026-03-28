#!/usr/bin/env bash

set -euo pipefail

if ! command -v lcov >/dev/null 2>&1 || ! command -v genhtml >/dev/null 2>&1; then
    echo "lcov and genhtml are required for coverage reporting (sudo apt install lcov)" >&2
    exit 1
fi

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

        # ── Debug + Coverage ─────────────────────────────────────
        echo "  [debug-cov] configure & build & test"
        cmake -S . -B target/debug-cov \
              -DCMAKE_BUILD_TYPE=Debug \
              -DENABLE_COVERAGE=ON \
              -Wno-dev
        cmake --build target/debug-cov
        ctest --output-on-failure --test-dir target/debug-cov
        # cmake 3.16 does not support `ctest --test-dir`, so use this workaround:
        # cmake -E chdir target ctest --output-on-failure

        # collect coverage from debug-cov build only
        rm -rf target/coverage
        mkdir -p target/coverage

        lcov --capture \
             --directory target/debug-cov \
             --base-directory . \
             --output-file target/coverage/coverage.info \
             --gcov-tool gcov \
             --no-external \
             --ignore-errors mismatch,mismatch \
             --ignore-errors source,source \
             --ignore-errors gcov,gcov \
             --quiet

           lcov --remove target/coverage/coverage.info \
             '*/third_party/*' \
             '*/test/*' \
             '*/target/*' \
             '*/src/catch2/*' \
               --output-file target/coverage/coverage_filtered.info \
             --quiet \
             --ignore-errors unused,unused

           genhtml target/coverage/coverage_filtered.info \
                 --output-directory target/coverage \
                --title "$dir Coverage" \
                --quiet

           echo "  Coverage HTML : $dir/target/coverage/index.html"
        cd ..
    fi
done