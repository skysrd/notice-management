package com.skysrd.noticemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NoticeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoticeManagementApplication.class, args);
	}

}
