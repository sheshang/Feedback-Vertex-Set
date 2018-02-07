#!/bin/bash

rm -rf bin
mkdir bin
javac -sourcepath src -d bin **/*.java
