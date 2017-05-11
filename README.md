# Intercom Coding - Customers JSON File

The exercise specified was:
> We have some customer records in a text file (customers.json) -- one customer per line, JSON-encoded. We want to invite any customer within 100km of our Dublin office for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

An example record looks like:
```json
{"latitude": "52.833502", "user_id": 25, "name": "David Behan", "longitude": "-8.522366"}
```

The program will ignore records which do not have a latitude and longitude specified.

## Implementation
The application is written in Java. The main class is `com.collinsrj.App`. 

## Usage
To run this application in development:
```bash
 ./gradlew run -Dexec.args=<file_location>
```

To build this application for distribution run:
```$bash
./gradlew assemble
```
This will generate a `IntercomTest.tar` in `/build/distributions/`. This contains everything you need to run the 
application. You can extract this using `tar -xvf IntercomTest.tar`. 

From this extracted folder, run the following `./bin/IntercomTest <file_location>` or 
`./bin/IntercomTest.bat <file_location>` depending on your platform.

