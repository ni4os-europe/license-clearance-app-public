package gr.uoa.di.madgik.lcapp;

import gr.uoa.di.madgik.lcapp.model.auth.Role;
import gr.uoa.di.madgik.lcapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity
public class LcappApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private RoleService roleService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LcappApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(LcappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Insert roles in our DB
		Role userRole = roleService.saveRole("ROLE_USER");
		Role adminRole = roleService.saveRole("ROLE_ADMIN");
	}
}
