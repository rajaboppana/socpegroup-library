/**
 * 
 */
package com.scopegroup.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Raja
 *
 *         Scope Group test
 * 
 *         Swagger documentation configuration class
 */

@Configuration
@EnableSwagger2
public class SwaggerDocumentConfig {

	/*
	 * Meta data for swagger
	 */
	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Scope Group").description("Scope Group assignment")
				.contact(new Contact("", "", "boppana.deu@gmail.com")).version("1.0").build();
	}
	
	/* 
	 * Custom Implementation by scanning all the REST controllers in the package 
	 * */
	 @Bean
	    public Docket customImplementation(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                    .apis(RequestHandlerSelectors.basePackage("com.scopegroup.library.controller"))
	                    .build()
	                .apiInfo(metadata());
	    }

}
