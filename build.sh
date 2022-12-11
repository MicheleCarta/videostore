#!/bin/sh
set -e

: ${VERSION:="latest"}
export VERSION

./gradlew

docker build --build-arg VERSION=${VERSION} -t product/video-store-service:${VERSION} .
