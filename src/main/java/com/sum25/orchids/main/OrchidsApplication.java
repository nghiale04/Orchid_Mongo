package com.sum25.orchids.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.sum25.orchids")
@EntityScan("com.sum25.orchids.entity")
@ComponentScan({"com.sum25.orchids.controller", "com.sum25.orchids.services", "com.sum25.orchids.repository", "com.sum25.orchids.config"})
@EnableMongoRepositories(basePackages = "com.sum25.orchids.repository")
public class OrchidsApplication {


	public static void main(String[] args) {
		SpringApplication.run(OrchidsApplication.class, args);
	}

}
