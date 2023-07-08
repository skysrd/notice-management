package com.skysrd.noticemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestNoticeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(NoticeManagementApplication::main).with(TestNoticeManagementApplication.class).run(args);
	}

}
