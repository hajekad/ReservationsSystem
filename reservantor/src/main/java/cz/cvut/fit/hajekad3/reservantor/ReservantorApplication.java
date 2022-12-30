package cz.cvut.fit.hajekad3.reservantor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.Api.Controllers")
public class ReservantorApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReservantorApplication.class, args);
	}

}
