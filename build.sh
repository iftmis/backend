#!/usr/bin/env bash
./mvnw -Pprod verify jib:dockerBuild
