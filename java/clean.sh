#!/usr/bin/env bash

set -euo pipefail

chmod +x ./mvnw
for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/pom.xml" ]; then
        echo "run mvn clean for $dir"
        ./mvnw -B -f "$dir/pom.xml" clean
    fi
done

echo "Java cleanup complete."
