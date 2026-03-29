#!/usr/bin/env bash

set -euo pipefail

for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/package.json" ]; then
        echo "Cleaning $dir"
        rm -rf "$dir/node_modules" "$dir/target"
    fi
done

echo "JavaScript cleanup complete."
