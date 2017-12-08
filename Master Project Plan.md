Plan for the Master Project

1. Core (Antidote Client, Docker, MapDB)

2. GUI

3. YCSB

4. Results Visualization

5. Benchmark Configurations for Antidote

Core Tasks

1. Finish implementation of the basic Docker Interface
-> Kevin Bartik
2. Finish implementation of the basic Antidote Client
-> Alka Scaria
3. Ensure that MapDB is working correctly when using concurrency
-> Kevin Bartik
4. Implement the management of Dockerfiles and Images so that multiple branches/commits of the Antidote database can be benchmarked 
-> Kevin Bartik
5. Implement the management of configuration files 
-> Alka Scaria
6. Add configuration implementation to the Antidote Client 
-> Alka Scaria
7. Integration of the YCSB interface 
-> Kevin Bartik, Alka Scaria
8. Feature Additions and Code Refactoring 
-> Kevin Bartik, Alka Scaria
9. Document the important functionality 
-> Kevin Bartik, Alka Scaria

GUI Tasks

1. Finish implementation of the basic GUI. 
-> Kevin Bartik
2. Extend the GUI to support new functionality in Benchmarking 
-> Kevin Bartik




YCSB Tasks

1. Create a Document that explains the YCSB Benchmark. 
-> Vishnu
2. Describe the Interface that must be implemented. 
-> Vishnu
3. Describe the configuration file that is used. 
-> Vishnu
4. Integrate the necessary features in the Core. 
-> All
5. Implement the interface for one datatype. 
-> Vishnu
6. Implement the interface for another datatype 
-> Vishnu
7. Implement the interface for most datatypes 
-> All

Results Visualization Tasks

1. Research existing implementations for Results Visualization (Java) 
-> Maxime
2. Implement examples for evaluation 
-> Maxime
3. Write a document about how the data must be structured to be visualized 
-> Maxime
4. Integrate the Visualization in the GUI 
-> Maxime, Kevin Bartik

Benchmark Configurations for Antidote

1. Define possible Benchmark Configurations for Antidote (Data Centers, Nodes, Connections, ...) 
-> All
2. Implement additional Benchmark Configurations in YCSB and Core 
-> All


Goals for the next 2 weeks

Core -> 1, 2, 3
GUI -> 1
YCSB -> 1, 2, 3
Results Visualization -> 1, 2
 
Goal for the next 3 weeks after that

Core -> 4, 5, 6, 7
YCSB -> 4, 5
Result Visualization -> 3, 4
Benchmark Configurations -> 1

Goal for the next 3 weeks after that

Core -> 8, 9
GUI -> 2
YCSB -> 6, 7
Benchmark Configurations -> 2

Goal for the rest of the time

Run Benchmarks
Make Code more extensible
Maybe write some automated Benchmarking and Tests
