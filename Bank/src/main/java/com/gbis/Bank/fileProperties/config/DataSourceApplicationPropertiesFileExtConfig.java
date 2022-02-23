package com.gbis.Bank.fileProperties.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import com.gbis.Bank.utils.HelperClass;

@Configuration
public class DataSourceApplicationPropertiesFileExtConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceHolderConfigurer() throws IOException {
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		String applicationPropertiesPath = HelperClass.getFilePath("BankProperties.properties");
		properties.setLocation(new FileSystemResource(applicationPropertiesPath));
		properties.setIgnoreResourceNotFound(false);
		return properties;
	}
}
