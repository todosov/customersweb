package com.website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by tadasyan
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.website.repository")
public class JpaConfiguration {
}
