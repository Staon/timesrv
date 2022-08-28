#!/bin/bash

mvn clean package
docker build \
    -t <some docker repo>/time-service:v1 \
    -f ./docker/Dockerfile \
    .
