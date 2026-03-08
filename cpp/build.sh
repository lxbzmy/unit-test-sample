#!/usr/bin/env bash

set -euo pipefail

for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/CMakeLists.txt" ]; then
    echo "Building and testing $dir"
    cd "$dir"
    cmake -S . -B target
    cmake --build target
    ctest --output-on-failure --test-dir target
    # cmake 3.16 does not support `ctest --test-dir`, so use this workaround:
    # cmake -E chdir target ctest --output-on-failure
    cd ..
    fi
done