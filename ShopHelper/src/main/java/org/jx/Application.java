package org.jx;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class Application {
		
		@Bean
		public PlatformTransactionManager txManager(DataSource dataSource){
			return new DataSourceTransactionManager(dataSource);
		}
		
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
}
