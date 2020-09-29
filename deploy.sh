#!/usr/bin/env bash
docker-compose -f src/main/docker/app.yml down --remove-orphans
docker-compose -f src/main/docker/app.yml up
