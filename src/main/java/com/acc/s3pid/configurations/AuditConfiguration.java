package com.acc.s3pid.configurations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", modifyOnCreate = false)
public class AuditConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuditConfiguration.class);

	@Bean
    public AuditorAwareImpl auditorProvider(){
        return new AuditorAwareImpl();
    }
    
    /*
    @Bean
	import java.util.Optional;
	import org.springframework.data.domain.AuditorAware;
    public AuditorAware auditorProvider() {
    	UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userPrincipal != null) {
			return userPrincipal.getId();
		} else {
			return 0L;
		}
	}
	*/
}