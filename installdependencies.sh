#!/bin/bash

cd MavenDependenciesAndPlugins

rm -rf YCSB-Embedded
git clone https://github.com/FairPlayer4/YCSB.git YCSB-Embedded --depth 1
cd YCSB-Embedded/core
mvn install

cd ../..

rm -rf Log4jShadeMavenPlugin
git clone https://github.com/FairPlayer4/maven-shaded-log4j-transformer.git Log4jShadeMavenPlugin --depth 1
cd Log4jShadeMavenPlugin
mvn install

cd ..

rm -rf IntellijUIDesignerMavenPlugin
git clone https://github.com/FairPlayer4/ideauidesigner-maven-plugin IntellijUIDesignerMavenPlugin --depth 1
cd IntellijUIDesignerMavenPlugin
./install-intellij-libs.sh
