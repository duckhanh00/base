package com.example.base;

import com.example.base.entity.Server;
import com.example.base.enumeration.Status;
import com.example.base.store.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories
public class BaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			serverRepository.save(new Server(null, "192.168.1.168", "Ubuntu Linux", "128 GB", "Laptop", "http://localhost:8080/assets/images/server1.png", Status.SERVER_DOWN, new Date()));
			serverRepository.save(new Server(null, "192.168.1.169", "MacBook Pro M1", "256 GB", "Laptop", "http://localhost:8080/assets/images/server2.png", Status.SERVER_UP, new Date()));
			serverRepository.save(new Server(null, "192.168.1.170", "MacBook Pro M2", "512 GB", "Laptop", "http://localhost:8080/assets/images/server3.png", Status.SERVER_DOWN, new Date()));
			serverRepository.save(new Server(null, "192.168.1.171", "MacBook Full Option", "1 TB", "Laptop", "http://localhost:8080/assets/images/server1.png", Status.SERVER_UP, new Date()));
		};
	}
}
