package com.xmz.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xmz.application.mapper")
public class DataRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataRecordApplication.class, args);
	}

}
