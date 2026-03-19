Part A – Architecture Warm-up

1. What responsibilities belong in Controller, Service, and Repository layers?
   . Controller Handles Http request and response and mapping input(request payload) to service call.
   . Service contains business logic and validation.
   . Repository interacts with database and perform CRUD operation.
   . By Using Controller, Service and Repository, it improves maintainability and testability.

2. What is a database transaction? When should @Transactional be used or avoided?
  . Transaction ensure database operations either all succeed or all fail.
  . multiple operations must remain consistent.
  . It should be avoided for simple read operations or where not needed, to reduce overhead.


3. Why are DTOs used instead of returning JPA entities directly from APIs?
  . DTOs are using to control what data is coming from the API.
  . They prevent leaking internal entity structures.
  . They allow flexibility in shaping request/response formats.
  . They improve security.
  . By using DTOs it is improving decouple API from database schema.

4. What problem does Liquibase solve in a multi-developer / multi-environment setup?
  . It ensures consistency across multiple environments and developers.
  . It prevents conflicts and manual database updates.
  . It provides traceability of schema evolution. 

