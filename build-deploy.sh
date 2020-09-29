#!/usr/bin/env bash
./mvnw -Pprod verify jib:dockerBuild
docker-compose -f src/main/docker/app.yml down --remove-orphans
docker-compose -f src/main/docker/app.yml up
