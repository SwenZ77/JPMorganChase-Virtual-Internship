# Midas
Project repo for the JPMC Advanced Software Engineering Forage program

# Task 1:
    i: Adding all mentioned dependencies\
    ii: Adding "general.kafka-topic: topic" to "application.yml"

# Task 2:
    i: Create a KafkaListen class
    ii: Set up De-serializer for Consumer at application.yml(via JSON)
# Task 3:
    i: Add H2 database dependency in pom.xml
    ii: Create a TransactionRecord entity class
    iii: Create a TransactionRepo
    iv: Create a "TransactionConduit.java" which will validate and save the transaction(transactionrecord) and update user details (balance)
    v: Inside the "listen" method in "KafkaListen.java" implement the method from TransactionConduit class