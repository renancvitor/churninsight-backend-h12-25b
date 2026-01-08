package equipe25.churninsight_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ChurninsightBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChurninsightBackendApplication.class, args);
	}

}
