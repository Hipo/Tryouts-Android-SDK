#!/usr/bin/env bash

# First build project
./jenkins/build.sh

# Then sign and upload to Tryouts.io
./jenkins/sign-and-upload.sh