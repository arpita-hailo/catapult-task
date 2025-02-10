This test automation project consists of 2 modules
1. functional-test
2. load-test

Tools/Libraries used :
1. Java
2. Maven
3. Junit
4. Cucumber
5. RestAssured
6. SpringBoot
7. Gatling

Running tests:
1. functional test
   navigate inside functional-test module 'cd functional-test'
   run this command 'mvn clean verify'
   This will run all the functional tests and generate a html report 'overview-features.html' under 'functional-test/target/cucumber-html-reports/' directory

2. load test
   navigate inside load-test module 'cd load-test'
   run this command 'mvn clean gatling:test'
   This will run all the load tests and generate a html report 'index.html' under 'load-test/target/gatling/catapultloadtest-[date]/' directory


Bugs encountered:
1. create book with new/existing author does not return the author details in the response
but get book returns the author details
2. create auther with new/existing book does not return the book details in the response
   but get auther returns the book details
3. 