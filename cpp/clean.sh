#!/usr/bin/env bash

set -euo pipefail

for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/CMakeLists.txt" ]; then
        echo "Cleaning $dir"
        rm -rf "$dir/target"
    fi
done

echo "CPP cleanup complete."
