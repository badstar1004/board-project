package com.board.boardproject.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // JpaAuditing 활성화
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때 수정
        return () -> Optional.of("uno");
    }
}
