#!/bin/env bash
export MAVEN_USER_HOME=$(pwd)/m2
for dir in *; do
    if [ -d "$dir" ] && [ -f "$dir/pom.xml" ]; then
    echo "Building and testing $dir"
    ./mvnw -f "$dir/pom.xml" package
    fi
done
