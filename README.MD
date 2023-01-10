### crud-spring-boot

This is a simple CRUD using Spring Boot and H2.

#### How to run

after clone the project, if you use Windows, run the following command:

```bash
./gradlew bootRun
```

otherwise, make sure that you have Gradle installed and run the following command:

```bash
gradle bootRun
```

#### Endpoints

- `GET /pagamento`: list all payments

- `POST /pagamento`: create a new payment

- `PUT /pagamento/{id}`: update the status of a payment

- `DELETE /pagamento/{id}`: delete a payment

#### TODO

- [ ] Add filter to `GET /pagamento`
- [ ] Add tests
- [ ] Standardize the error handling
