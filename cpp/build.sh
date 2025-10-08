#!/bin/env bash
for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/CMakeLists.txt" ]; then
    echo "Building and testing $dir"
    cd "$dir"
    cmake -S . -B target
    cmake --build target
    ctest --output-on-failure --test-dir target
    cd ..
    fi
done