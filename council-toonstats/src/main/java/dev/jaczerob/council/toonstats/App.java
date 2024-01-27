package dev.jaczerob.council.toonstats;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class App {
	public static void main(final String... args) {
		new SpringApplicationBuilder(App.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}

	@Scheduled(cron = "0 0 */60 * * *")
	public void monitorToons() {

	}
}
