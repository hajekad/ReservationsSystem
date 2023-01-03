package cz.cvut.fit.hajekad3.reservantor;

import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
public class ReservantorApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReservantorApplication.class, args);
	}

}
