#!/bin/sh

NAME="ImageEditor"

javac -d "out/production/$NAME" $(find src/main/java -name '*.java')
cp -r src/META-INF "out/production/$NAME"

rm -rf out/jar
mkdir out/jar

cd "out/production/$NAME"
jar cmvf META-INF/MANIFEST.MF "../../jar/$NAME.jar" ./
cd -

