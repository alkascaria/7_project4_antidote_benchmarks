# Installation Instructions for AntidoteBenchmark<br />

Before you can run mvn install or mvn package on the maven project in AntidoteBenchmark you must install some additional dependencies.<br />

Just run installdependencies.sh (Windows -> run with gitbash alias Git for Windows) to install all necessary dependencies.

Two small git repos will be cloned and some maven libraries are installed to the local maven repository

Link to solution explanation -> https://stackoverflow.com/questions/32747917/intellij-gui-designer-maven-executable-jar-export/45125398#45125398 <br />

Link to repository -> https://github.com/jorichard/ideauidesigner-maven-plugin <br />

If everything worked you can now run mvn install or mvn package on the maven project in the folder AntidoteBenchmark.


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

