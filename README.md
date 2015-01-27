# goeuro-test
Test Application

Test Application contains the following frameworks and libraries
1. Spring fraimwork
2. Maven
3. guava-libraries

Compile/Package and run

1. (optional) go to src/main/filters directory edit local.filter.goeuro and change the enpoint url or CSV file name and path if necessary

2. compile by the following command
    mvn clean package

3. go to target directory by the following command
    cd target

4. run the test by the following command
    java -jar GoEuroTest-1.0-SNAPSHOT-jar-with-dependencies.jar CITY_NAME
