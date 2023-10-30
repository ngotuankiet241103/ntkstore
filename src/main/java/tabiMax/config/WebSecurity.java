package tabiMax.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import tabiMax.auth.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurity {
	@Autowired
	private CustomUserDetailService userDetailsService;
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable()
				.authorizeRequests(authorize -> authorize.antMatchers("/trang-chu").permitAll()
						.antMatchers("/admin/**").hasAnyAuthority("management:read")
						.antMatchers("/login").permitAll()
						.antMatchers("/authenticated").permitAll().antMatchers("/refresh-token").permitAll()
						.antMatchers("/register").permitAll().antMatchers("/registerAccount").permitAll()
						.antMatchers("/api/product/**").authenticated().antMatchers("/api/**").permitAll())
				.formLogin(form -> form.loginPage("/admin/authenticated").permitAll().passwordParameter("password")
						.usernameParameter("email"))
//				.antMatcher("/**").sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.antMatcher("/admin/**").sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//				.and()

				.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
					if (request.getRequestURL().indexOf("admin") > 0) {
						response.sendRedirect(request.getContextPath() + "/admin/authenticated");
						return;
					}
					response.setContentType("application/json;charset=UTF-8");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}).and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).logout()
				.logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
//		http.cors().disable().csrf().disable()
//
//				.authorizeHttpRequests(authz -> authz.antMatchers("/trang-chu").permitAll().antMatchers("/admin*")
//						.authenticated().antMatchers("/login").permitAll().antMatchers("/authenticated").permitAll()
//						.antMatchers("/refesh-token").permitAll().antMatchers("/register").permitAll()
//						.antMatchers("/registerAccount").permitAll().antMatchers("/api/product/**").authenticated()
//						.antMatchers("/api/**").permitAll()
//
//						.antMatchers("/admin/manager/user").hasRole("ADMIN")
////						.antMatchers("/admin/**")
////						.hasAuthority("management:read")
//
//				)
//				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//				.formLogin(form -> form.loginPage("/authenticated").permitAll().passwordParameter("password")
//						.usernameParameter("email"))
//				.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
//					if(request.getRequestURL().indexOf("admin") > 0) {
//						response.sendRedirect(request.getContextPath() +  "/authenticated");
//						return;
//					}
//					response.setContentType("application/json;charset=UTF-8");
//					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
////					response.getWriter().write(new  HashMap<String, LocalDateTime>().put("timestamp", LocalDateTime.now()));
//				}).and()
//				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).logout()
//				.logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
//				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

		return http.build();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {

			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {

				response.sendRedirect("/authenticated");
			}
		};
	}

	public class HandleAccessDenied implements AccessDeniedHandler {

		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response,
				AccessDeniedException accessDeniedException) throws IOException, ServletException {
			new RuntimeException("access deny");
			String url = request.getRequestURL().toString();
			response.sendRedirect("avca");

		}

	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setUseReferer(true);
		return auth;
	}

}
