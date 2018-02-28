# Installation Instructions for AntidoteBenchmark<br />

Before you can run mvn install or mvn package on the maven project in AntidoteBenchmark you must install four libraries and plugin to maven.<br />

First go to the folder Maven_Dependencies_Plugins.<br />

There is a folders named intellij<br />

In this folder there will be a .bat/.sh script file that will perform the necessary installations. 

Alternatively the commands for manual installation are listed below:

Open the command line inside the folder intellij.<br />

Run the following 4 commands (after the arrow) (note that %current_path% means the full path to that file and you have to insert that for your system) <br />

1. -> mvn install:install-file -Dfile="%current_path%\javac2.jar" -DgroupId=com.intellij -DartifactId=javac2 -Dversion=17.1.5 -Dpackaging=jar
<br /><br />
2. -> mvn install:install-file -Dfile="%current_path%\asm-all.jar" -DgroupId=com.intellij -DartifactId=asm-all -Dversion=17.1.5 -Dpackaging=jar
<br /><br />
3. -> mvn install:install-file -Dfile="%current_path%\forms_rt.jar" -DgroupId=com.intellij -DartifactId=forms_rt -Dversion=17.1.5 -Dpackaging=jar
<br /><br />
4. -> mvn install
<br /><br />

The first three commands install the necessary Intellij libraries for the UI designer<br />

The 4. command installs the maven plugin that allows compiling and packaging the project with maven<br />

Link to solution explanation -> https://stackoverflow.com/questions/32747917/intellij-gui-designer-maven-executable-jar-export/45125398#45125398 <br />

Link to repository -> https://github.com/jorichard/ideauidesigner-maven-plugin <br />

After that you can most likely run mvn install or mvn package on the maven project in the folder AntidoteBenchmark.





https://syncfree.github.io/antidote/  <br />
https://arewefastyet.com/  <br />
https://github.com/brianfrankcooper/YCSB/wiki  <br />
https://github.com/basho/basho_bench  <br />
https://www.docker.com/  <br />
https://github.com/SyncFree/antidote

java benchmarking frameworks  <br />

https://github.com/brianfrankcooper/YCSB  <br />
http://jmeter.apache.org/  <br />

Antidote java client  <br />
https://github.com/SyncFree/antidote-java-client


Guide for getting the project to run

Install Docker
run the following command which download the image of antidote:
docker pull antidotedb/antidote

Open Docker Settings (Right-Click Icon in Tray and select Settings...) and Check the last Box which allows the Docker Java Client to connect to localhost containers

Install git (probably SmartGit or some other GUI)

Checkout the Project https://softech-git.informatik.uni-kl.de/students/17_project4_antidote_benchmarks.git

Install Intellij IDEA Community Edition (Free, Open Source)
https://www.jetbrains.com/idea/download/
(Students can get a free licence for Ultimate by applying with their RHRK E-Mail) -> I use the Community Edition

In Intellij IDEA open (third option) the Project by selecting the Folder AntidoteBenchmark that is in the Checkout Repository and then pressing OK

Then the project should be opened and you should be able to run it

Tell me if there are any problems

