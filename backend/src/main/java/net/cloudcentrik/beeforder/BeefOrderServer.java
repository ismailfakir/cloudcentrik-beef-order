package net.cloudcentrik.beeforder;

import net.cloudcentrik.beeforder.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Import(WebConfig.class)
public class BeefOrderServer {

	public static void main(String[] args) {
		SpringApplication.run(BeefOrderServer.class, args);
	}

}
