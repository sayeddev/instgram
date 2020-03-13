package com.sayedlabs.Instgram;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.autoconfigure.mongo.*;
import org.springframework.boot.autoconfigure.data.mongo.*;
@SpringBootApplication
@EnableSwagger2
public class InstgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstgramApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sayedlabs.Instgram.controller"))
                .build();
    }

    @Bean
    public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
