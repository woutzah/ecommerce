package com.blockken.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class EcomBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomBackendApplication.class, args);
	}

//  @Bean
//  public DataSource ensureSchemaExists(DataSource dataSource) throws SQLException {
//    try (Connection connection = dataSource.getConnection();
//         Statement stmt = connection.createStatement()) {
//      stmt.execute("CREATE SCHEMA IF NOT EXISTS ecommerce");
//    }
//
//    return dataSource;
//  }

}
