#!/bin/bash

# Exit on error
set -e

# Work off travis
if [[ ! -z TRAVIS_PULL_REQUEST ]]; then
  echo "TRAVIS_PULL_REQUEST: $TRAVIS_PULL_REQUEST"
else
  echo "TRAVIS_PULL_REQUEST: unset, setting to false"
  TRAVIS_PULL_REQUEST=false
fi

# Copy mock google-services file
echo "Using mock google-services.json"
cp mock-google-services.json app/google-services.json

# Build
if [ $TRAVIS_PULL_REQUEST = false ] ; then
  echo "Building full project"
  # For a merged commit, build all configurations.
  ./gradlew clean build
else
  # On a pull request, just build debug which is much faster and catches
  # obvious errors.
  ./gradlew clean assembleDebug check
fi
