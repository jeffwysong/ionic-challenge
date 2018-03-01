Description
============
Problem Background: Given as input a two-dimensional array of values (that represent, say, measurements
of some signal strength over each cell in a region), we wish to automatically generate a description of the subregions
of interest, defined as all contiguous groups of adjacent cells for which the signal is greater than a
threshold value T. Cells that are touching at corners are considered to be (diagonally) adjacent cells. 

I assumed that if the input matrix contained a null value instead of an int, I changed that to Integer.MIN_VALUE so that
it would never be above a given threshold.

Problem
========
1. Write down a function that takes two inputs:
• a two-dimensional array of values
• a threshold T
 and determines this output:
• A list of (X,Y) value pairs that identify the center of mass of each region of interest. (There will be
one X,Y coordinate pair for each sub-region.) Define "center of mass" for a given sub-region to be
the average position, expressed in X,Y coordinates, of the cells in that sub-region, each cell's location
being weighted by that cell's signal value.
Feel free to express your response as compilable code. (If you do, please include any make or project files.)

Please see below instructions on how to run the rest api with httpie or curl examples to test out the functionality.
If just the center of mass is desired for each sub-region, please use the http://localhost:8080/centermass endpoint.
To technically answer the question, please see DefaultIonicChallengeManager for the actual function implementation.

2. Discuss a preferred strategy for testing the function you defined in part 1, including how you might
automate the testing, how you would select test input cases (feel free to enumerate cases), and how you would
determine whether each test case results in a pass or fail. 

My preferred strategy for testing is using is verifying that the happy cases work and then test with edge cases and
known bad input.  
I created integration tests to cover the happy cases for input and a few cases of bad inputs.  Manual tests can be
done using httpie or curl and if bugs are found, tests can be added to the IonicChallengeApplicationTests class
to cover those cases.  I would consider test cases to pass if the adjacent points are returned correctly and the
center of mass found.


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
