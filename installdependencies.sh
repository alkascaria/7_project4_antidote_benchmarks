#!/bin/sh


cd MavenDependenciesAndPlugins
if cd YCSB-Embedded; then git pull; else git clone https://github.com/FairPlayer4/YCSB.git YCSB-Embedded; fi
cd YCSB-Embedded # only necessary if the repository did not exist yet
cd core
mvn install
cd ..
cd ..
cd IntellijUIDesignerJarFiles
mvn install:install-file -Dfile="$PWD/javac2.jar" -DgroupId=com.intellij -DartifactId=javac2 -Dversion=17.1.5 -Dpackaging=jar
mvn install:install-file -Dfile="$PWD/asm-all.jar" -DgroupId=com.intellij -DartifactId=asm-all -Dversion=17.1.5 -Dpackaging=jar
mvn install:install-file -Dfile="$PWD/forms_rt.jar" -DgroupId=com.intellij -DartifactId=forms_rt -Dversion=17.1.5 -Dpackaging=jar
cd ..
if cd IntellijUIDesignerMavenPlugin; then git pull; else git clone https://github.com/jorichard/ideauidesigner-maven-plugin.git IntellijUIDesignerMavenPlugin; fi
cd IntellijUIDesignerMavenPlugin # only necessary if the repository did not exist yet
mvn install