**Bug Report**

| Bug Id | Summary | Steps to Reproduce | Expected Result | Actual Result | Environment | Priority |
| :---- | :---- | :---- | :---- | :---- | :---- | :---- |
| 1 | create book with new/existing author does not return the author details in the response | Perform createBook mutation with author details | Book and author  gets created/linked successfully and the response contains the book and author details | Book and author  gets created/linked successfully but the response does not contain  author details | http://demo-api.tc.staging.catapult.com:3000/graphql | High |
| 2 | create author with new/existing book does not return the book details in the response | Perform createAuther mutation with book details | Book and author  gets created/linked successfully and the response contains the book and author details | Book and author  gets created/linked successfully but the response does not contain  book details | http://demo-api.tc.staging.catapult.com:3000/graphql | High |
| 3 | UpdateBook mutation is not updating the Book details \- Intermittent | Perform UpdateBook mutation to update any field with valid data e.g. title | Book detail should get updated everytime consistantly | It fails intermittently | http://demo-api.tc.staging.catapult.com:3000/graphql | High |
| 4 | Error is thrown when “\_count” field of Auther is queried | Include \_count field of Auther in any of the applicable query/mutation | The response should display the appropriate data with \_count field | Observe below error is thrown. "errors": \[    { "message": "Cannot return null for non-nullable field Auther.\_count."}\] | http://demo-api.tc.staging.catapult.com:3000/graphql | Medium |
| 5 | Performance Issue: As a result of Bug-3 load test was reporting around 5% failure | Run the load test for 15 min | Failure percentage should be less than 1% | Failure percentage was reported 5.4% | http://demo-api.tc.staging.catapult.com:3000/graphql | Critical |

Observation:

1. Error handling is not proper- instead of showing specific error, the API is returning the whole stack trace.  
2. Author name and Book title is accepting input of any length of characters (tested with 3000 characters).

Go/No-Go decision: **No-Go**

The overall state of the API is ok, but there are clearly some critical and high priority open defects that need to be addressed before we can release the service to production.  
Performance issue (bug \- 5\) is the most concerning and hence marked as critical. The current failure percentage is above 5% which is not acceptable for release to go ahead in production.  
Load test and functional test reports are attached in the git repo for reference.  
