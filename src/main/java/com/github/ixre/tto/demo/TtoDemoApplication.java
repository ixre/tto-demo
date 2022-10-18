package com.github.ixre.tto.demo;

import net.fze.common.Standard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.github.ixre.tto.demo.repo"})
@EntityScan("com.github.ixre.tto.demo.entity")
public class TtoDemoApplication {

	public static void main(String[] args) {
		Standard.resolveEnvironment(TtoDemoApplication.class);
		SpringApplication.run(TtoDemoApplication.class, args);
	}

}
