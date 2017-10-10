package com.cimri.challange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;

@Component
@TestPropertySource("classpath:application.properties")
public class AppProperties {

	@Value("${TEST_SITE_URL}")
	private String testSiteUrl;

	public String getTestSiteUrl() {
		return testSiteUrl;
	}

	public void setTestSiteUrl(String testSiteUrl) {
		this.testSiteUrl = testSiteUrl;
	}

}
