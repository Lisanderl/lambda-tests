#!/bin/bash
#docker build . -t sensor-lambda-func
mkdir -p build
docker run --rm --entrypoint cat sensor-lambda-func  /home/application/function.zip > build/function.zip
docker run --rm --entrypoint cat sensor-lambda-func  /usr/src/app/function.zip > build/function.zip