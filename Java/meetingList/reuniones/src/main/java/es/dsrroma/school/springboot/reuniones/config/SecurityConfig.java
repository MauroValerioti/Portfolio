package es.dsrroma.school.springboot.reuniones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(SecurityConfig.class);

	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/api/*", "/api/rest/**")
				.hasRole("API_USER").and().authorizeHttpRequests()
				.requestMatchers("/*", "/actuator/**").authenticated()
				.and().formLogin(login -> login.loginPage("/login").permitAll())
				.logout(logout -> logout.permitAll());


		return http.build();

	}

	// esta es la nueva configuracion avalada por Spring Security
	@Bean
	UserDetailsService userDetailsService() {
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("Jefe").password(passwordEncoder().encode("123")).roles("USER", "API_USER").build());
		manager.createUser(
				User.withUsername("empleado").password(passwordEncoder().encode("asd")).roles("USER").build());
		return manager;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
