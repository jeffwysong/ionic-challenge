Description
============
Problem Background: Given as input a two-dimensional array of values (that represent, say, measurements
of some signal strength over each cell in a region), we wish to automatically generate a description of the subregions
of interest, defined as all contiguous groups of adjacent cells for which the signal is greater than a
threshold value T. Cells that are touching at corners are considered to be (diagonally) adjacent cells. 

I assumed that if the input matrix contained a null value instead of an int, I changed that to Integer.MIN_VALUE so that
it would never be above a given threshold.

Requirements
============

* Java 1.8
* Apache Maven 3.5.2

Instructions
============
To run the rest api from the command line:

    mvn clean spring-boot:run

Your server url will be: localhost:8080

In another terminal you can test the api.
Here is an example list of httpie commands for the different endpoints:

    http POST localhost:8080/adjacents < src/main/resources/input.json
    http POST localhost:8080/centermass < src/main/resources/input2.json
    http POST localhost:8080/centermass/adjacents < src/main/resources/input.json

Here are the same commands in curl:

    curl -XPOST -H "Content-type: application/json" -d '{
      "inputMatrix": [[5, 0, 25],[null, 5, 95],[-15, 5, 175],[5, 5, 145]],
      "threshold": 4
    }' 'http://localhost:8080/adjacents'
    
    curl -XPOST -H "Content-type: application/json" -d '{
      "inputMatrix": [[5, 0, 25, 5, 145, 250],
        [0, 5, 95, 115, 165, 250],
        [15, 5, 175, 250, 185, 160],
        [5, 5, 145, 250, 245, 140],
        [115, 210, 60, 5, 230, 220],    
        [55, 5, 175, 150, 170, 145]],
      "threshold": 200
    }' 'http://localhost:8080/centermass'
    
    curl -XPOST -H "Content-type: application/json" -d '{
      "inputMatrix": [[5, 0, 25],[0, 5, 95],[-15, 5, 175],[5, 5, 145]],
      "threshold": 4
    }' 'http://localhost:8080/centermass/adjacents'
    

To run the integration tests:

    mvn test

If you can open the project with IntelliJ IDEA, you can run IonicChallengeApplicationTests with Coverage and see that 
I have 100% coverage of everything in the business package.
