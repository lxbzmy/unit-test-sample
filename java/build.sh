#!/usr/bin/env bash

set -euo pipefail

export MAVEN_USER_HOME="$(pwd)/m2"
chmod +x ./mvnw
for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/pom.xml" ]; then
        echo "run mvn site for $dir"
        ./mvnw  -B -f "$dir/pom.xml" clean site
    fi
done
