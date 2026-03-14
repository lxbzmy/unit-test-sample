#!/usr/bin/env bash

set -euo pipefail

for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/package.json" ]; then
        echo "Building and testing $dir"
        (
            cd "$dir"
            npm install --no-audit --no-fund
            npm test
        )
    fi
done
