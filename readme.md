This test automation project consists of 2 modules
1. functional-test
2. load-test

### Tools/Libraries used :
1. Java
2. Maven
3. Junit
4. Cucumber
5. RestAssured
6. SpringBoot
7. Gatling

### Running tests:

#### 1. functional test

   navigate inside functional-test module `cd functional-test`
   run this command `mvn clean verify`
   This will run all the functional tests and generate a html report '_overview-features.html_' under '_functional-test/target/cucumber-html-reports/_' directory

I have attached the report from my last run here [overview-features.html](test-reports/cucumber-html-reports/overview-features.html)

#### 2. load test

   navigate inside load-test module `cd load-test`
   run this command `mvn clean gatling:test`
   This will run all the load tests and generate a html report '_index.html_' under '_load-test/target/gatling/catapultloadtest-[date]/_' directory

I have attached the report from my last run here [index.html](test-reports/catapultloadtest-20250210150719286/index.html)


### Test Plan:

Test plan is attached here [Test_Plan.md](Test_Plan.md)

### Bugs encountered:

Bug report is attached here [catapult_bugs.md](catapult_bugs.md)