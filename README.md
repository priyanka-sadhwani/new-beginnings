# New Beginnings - Registry Service
A service to handle the participant registry with APIs to support adding, retrieving, updating and removing a patient from the registry.

#### Instructions
This project uses Maven with Java 11. Make sure you are using Java 11 or higher. Run the command 'mvn clean compile' or use the Maven tool window to clean and compile the project. 
To run the service, simply run RegistryApp.java. The APIs are hosted on http://localhost:8080. 6 APIs exists:

1. Retrieve all participants: GET, http://localhost:8080/participants
2. Retrieve a participant by reference number: GET, http://localhost:8080/participants/{refNumber}
3. Add new participant: POST, http://localhost:8080/participants, request body should match Participant object, see src/test/resources/jane.json for an example.
4. Update participant: PUT, http://localhost:8080/participants, , request body should match updated Participant object, see src/test/resources/jane.json for an example.
5. Update phone number and/or address of participant: PATCH, http://localhost:8080/participants/{refNumber}, optional query parameters are phoneNumber and address for whichever ones you want to update.
6. Remove a participant: DELETE, http://localhost:8080/participants/{refNumber}

#### Framework
I have chosen Spring Boot over Dropwizard as it is quicker to get off the ground into a working service, and also supports dependency injection out of the box without using any other external libraries.
The trade-off is that Dropwizard is more customizable than Spring Boot but I decided this trade-off was worth it, given the simplicity of the service.
#### Repository 
As a simple first implementation, the repository to store participants used is an internal in-memory map object. I also considered using an in-memory H2 database, but opted for the simple approach to start off with, intending to switch to a H2 database later on, but running out of time. 
A persistence layer backed up by an actual database should be implemented, so the data doesn't get lost every time the server gets restarted. I have structured the code in such a way it is easy to plug in another repository type without any change to the business logic.
#### Testing
Each of the components (controller, service, repository) of the app are unit tested separately, using Mockito to mock the dependencies. TDD was used for the service testing which contains the large majority of the business logic. The repository tests and controller tests (unfinished) were implemented afterwards. 
I also planned to write a web integration test (RegistryAppIntegrationTest) to test the actual exposed APIs using Spring Boots in built method (@WebIntegrationTest) but unfortunately also did not manage to get to that.
#### Additional enhancements
1. WebIntegrationTest
2. Validations on participant fields and error handling - not null/empty checks, date of birth in a range
3. Health Check
4. Swagger/API docs
5. Change address from String to a class (i.e. firstLine, secondList, postcode, etc)




  

   



