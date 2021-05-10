package com.automate.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.automate.util.GsUtil;
import com.automate.util.impl.GsUtilImpl;




/**
 *
 * @author srujan
 *
 */
@Configuration
public class AppConfig {
	
	@Bean
	public GsUtil gsUtil() {
		return new GsUtilImpl();
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
}
