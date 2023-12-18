package dev.jaczerob.council.discord;

import net.dv8tion.jda.api.JDA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
	public static void main(final String... args) throws Exception {
		final ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		context.getBean(JDA.class).awaitShutdown();
	}
}
