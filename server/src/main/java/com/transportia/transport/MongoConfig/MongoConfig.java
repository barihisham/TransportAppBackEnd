package com.transportia.transport.MongoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

public class MongoConfig {
    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
