## Capmo Automation Task Framework
The framework is for the Capmo Task

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
What things you need to install the software and how to install them
```
Maven
Java
Eclipse/IntelliJ
```

## Steps to execute the framework in your local


1. Checkout the code in your local using:
   **CodeSummit repo"**
2. Once checked out, import the project as "maven project" in eclipse/STS/IntelliJ
3. Once import is successfully, you can run pom.xml
4. In order to run all tests suite files or feature-wise suite file(s) for a particular component manually.
    - **To run all features at once, value to be passed is 'all'**
        - `Run the TestNG.xml file` OR
        - `Run the command mvn clean test -Dtestngfile="<testngfilename>"`

<h3><b>Note:</b></h3>


*  Values for the parameters passed when running from maven commandline:

   | **Parameter Name** | **Description** | **Values** |
       | ------ | ------ | ------ |
   | env | Which environment the tests to run on | At the moment only live env for Capmo |
   | browser | Which browser to run | chrome/firefox |
   | feature | Which class test cases to trigger | select from TestNG.xml |


<b>Note:</b> Drivers have been taken care of automatically and therefore, no special change is needed at your end. It will be taken care of automatically by the pom dependency.</font>


## Project Structure
As the project is maven based, have followed 'standard' maven project structure as below:

```
src/main/java       - Consists of Page classes & methods
src/main/resources  - Consists of resources utilised by across (such as log4j.properties, setup.properties etc.)   
src/main/test       - Consists of tests to be executed
```
## IMPORTANT POINTS

1. The framework can be integrated within the pipeline. 
2. There are many other combinations possible that could be added to this suite, but only limited have been taken care of for the task purpose. 
3. If any special change is needed, I would be happy to do so. 
4. The framework is data driven and all the data and dependencies have been taken care of automatically. Simply run the TestNG file and all the test results will be executed automatically.
5. The framework is platform and browser independent. But the tests have been run on Chrome and Windows.


## Built With
* Dependency Management - [Maven](https://maven.apache.org/)
* Web framework used    - Selenium WebDriver + PageFactory Model
* API Framework used    - [RestAssured](http://rest-assured.io/)
* Testing tool          - [TestNG](https://testng.org/doc/)
* Extent Reporting      - [Extent Reports](https://extentreports.com/)
* Logging               - [Log4J](https://logging.apache.org/log4j/2.x/)

	