package Cloud.Book_Search_Backend_API.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-resources/**", "/swagger-ui/**", "/webjars/**", "/v3/api-docs", "/hello/**", "/member/**", "/login/**").permitAll()
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증된 사용자만 접근 가능
                )
                .formLogin(form -> form
                        .loginPage("/login") // 사용자 정의 로그인 페이지를 설정하려는 경우
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }
}
