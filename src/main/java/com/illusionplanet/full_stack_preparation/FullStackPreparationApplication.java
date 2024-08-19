package com.illusionplanet.full_stack_preparation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.illusionplanet.full_stack_preparation.mapper")
public class FullStackPreparationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackPreparationApplication.class, args);
	}

}
