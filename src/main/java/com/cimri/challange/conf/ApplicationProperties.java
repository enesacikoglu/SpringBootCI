package com.cimri.challange.conf;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

	@Value("${keyspace_name}")
	private String keyspaceName;

	@Value("${contact_points}")
	private String contactPoints;

	@Value("${port}")
	private int port;

	@Value("${max_pool_connection}")
	private int maxPoolConnection;

	@Value("#{'${SITE_URLS}'.split(',')}")
	private List<String> siteUrls;

	@Value("${TEST_SITE_URL}")
	private String testSiteUrl;

	public List<String> getSiteUrls() {
		return siteUrls;
	}

	public String getTestSiteUrl() {
		return testSiteUrl;
	}

	public String getKeyspaceName() {
		return keyspaceName;
	}

	public String getContactPoints() {
		return contactPoints;
	}

	public int getPort() {
		return port;
	}

	public int getMaxPoolConnection() {
		return maxPoolConnection;
	}

}
