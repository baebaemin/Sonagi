package com.fa.sonagi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fa.sonagi.jwt.JwtAuthenticationFilter;
import com.fa.sonagi.jwt.JwtTokenProvider;
import com.fa.sonagi.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.fa.sonagi.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.fa.sonagi.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.fa.sonagi.oauth.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;
	private final RedisTemplate<String, String> redisTemplate;
	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors()
			.configurationSource(corsConfigurationSource());

		httpSecurity
			.httpBasic().disable() // 기본 설정된 것, JWT 토큰 사용할 거면 필요 없어서 disable
			.csrf().disable() // 보안 기능, JWT랑 OAuth 쓰면 필요 없음
			.formLogin().disable() // form 로그인 사용 X
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 X
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/login/**").permitAll()
			.requestMatchers("/**").hasAnyRole("USER", "ADMIN");

		httpSecurity
			.oauth2Login()
			.authorizationEndpoint()
			.baseUri("/oauth2/authorization") // 인가 요청을 해야하는 인가 요청url의 앞부분
			.authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
			.and()
			.redirectionEndpoint()
			.baseUri("/*/oauth2/code/*") // 인가요청이의 응답으로 인가코드를 보내오는 어플리케이션 등록시의 redirect uri
			.and()
			.userInfoEndpoint()
			.userService(customOAuth2UserService)
			.and()
			.successHandler(oAuth2AuthenticationSuccessHandler())
			.failureHandler(oAuth2AuthenticationFailureHandler());

		httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	// 쿠키 기반 인가 정보  Repository
	// 인가 응답을 연계하고 검증.
	@Bean
	public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
		return new OAuth2AuthorizationRequestBasedOnCookieRepository();
	}

	// Oauth 인증 성공 핸들러
	@Bean
	public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
		return new OAuth2AuthenticationSuccessHandler(
			oAuth2AuthorizationRequestBasedOnCookieRepository(),
			jwtTokenProvider,
			redisTemplate);
	}

	// Oauth 인증 실패 핸들러
	@Bean
	public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
		return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
	}

	//CORS 허용 적용
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}