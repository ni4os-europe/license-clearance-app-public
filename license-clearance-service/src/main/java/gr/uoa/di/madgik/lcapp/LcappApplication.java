package gr.uoa.di.madgik.lcapp;

import gr.uoa.di.madgik.lcapp.model.ContactCategory;
import gr.uoa.di.madgik.lcapp.model.Country;
import gr.uoa.di.madgik.lcapp.model.auth.User;
import gr.uoa.di.madgik.lcapp.security.CustomSuccessHandler;
import gr.uoa.di.madgik.lcapp.service.ContactCategoryService;
import gr.uoa.di.madgik.lcapp.service.CountryService;
import gr.uoa.di.madgik.lcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.sql.Timestamp;


@SpringBootApplication
@EnableWebSecurity
public class LcappApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	ContactCategoryService contactCategoryService;

	@Autowired
	CountryService countryService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LcappApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(LcappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create a new testuser
//		userService.deleteAll();
	}
}
