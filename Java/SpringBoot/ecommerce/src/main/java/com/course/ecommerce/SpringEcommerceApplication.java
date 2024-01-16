package com.course.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication // YA NO ES NECESARIO YA QUE SE REALIZO LA CONFIGURACION CORRESPONDIENTE EN "application.properties"(exclude = DataSourceAutoConfiguration.class)//esta ultima opcion agregada es para que no arroje un error al no estar vinculado a ninguna DB.
public class SpringEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEcommerceApplication.class, args);
	}

}
