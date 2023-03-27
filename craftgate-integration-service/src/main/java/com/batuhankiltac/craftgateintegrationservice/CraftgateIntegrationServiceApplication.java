package com.batuhankiltac.craftgateintegrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.batuhankiltac.craftgateintegrationservice")
public class CraftgateIntegrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftgateIntegrationServiceApplication.class, args);
	}

}
