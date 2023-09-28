package com.fundingForAll.www;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
* war를 빌드하기 위해서는 web.xml의 파일이 필요하다
* SpringBoot는 web.xml 대신 SpringBootServletInitializer이 web.xml의 역할을 수행한다.
* */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WwwApplication.class);
	}

}
