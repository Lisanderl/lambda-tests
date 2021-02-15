#!/bin/bash
#docker build . -t sensor-lambda
mkdir -p build
docker run --rm --entrypoint cat sensor-lambda  /home/application/function.zip > build/function.zip
