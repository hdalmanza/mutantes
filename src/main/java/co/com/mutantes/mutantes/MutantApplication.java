package co.com.mutantes.mutantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class MutantApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantApplication.class, args);
	}

}
