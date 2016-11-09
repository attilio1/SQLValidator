package com.qaframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.qaframework.processinquiry.Runner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private Runner runner;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setWebEnvironment(false);
		ConfigurableApplicationContext context = app.run(args);

		System.out.println("The End");
		context.close();
	}

	@Override
	public void run(String... arg0) throws Exception {
		runner.execute();
	}
}