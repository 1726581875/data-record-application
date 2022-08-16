package com.xmz.datarecordapplication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xmz.datarecordapplication.mapper")
public class DataRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataRecordApplication.class, args);
	}

}
