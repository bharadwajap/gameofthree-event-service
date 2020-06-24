# Getting Started
Reference Documentation for 'Game of Three' REST API game

This service is an Event producer and a REST API used by the players to create events to each other


PS: A local installtion of Rabbit MQ server is required for this service to run (To consume the messages and for the event service to publish). Messaging queue names has been mentioned in the configuration files.

### How to Build and Run
Project is build using Maven.

Run below commands to bring both the players up and running. Based on the configuration files, both players run on different
ports with different application names.

`java -jar gameofthree-event-service-0.0.1-SNAPSHOT.jar`
