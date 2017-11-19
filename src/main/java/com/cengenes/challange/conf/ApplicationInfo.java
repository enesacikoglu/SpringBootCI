package com.cengenes.challange.conf;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInfo {

	@Value("${sample.application.name}")
	private String applicationName;
	@Value("${sample.application.description}")
	private String applicationDescription;
	@Value("${sample.application.version}")
	private String applicationVersion;
	@Value("${sample.application.createdBy}")
	private String createdBy;
	@Value("${sample.application.reviewers}")
	private String[] reviewers;

	public String getApplicationName() {
		return this.applicationName;
	}

	public String getApplicationVersion() {
		return this.applicationVersion;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public String[] getReviewers() {
		return this.reviewers;
	}

	public String getApplicationDescription() {
		return this.applicationDescription;
	}

	@Override
	public String toString() {
		return "Application [applicationName=" + this.applicationName + ", applicationDescription=" + this.applicationDescription + ", applicationVersion="
				+ this.applicationVersion + ", createdBy=" + this.createdBy + ", reviewers=" + Arrays.toString(this.reviewers) + "]";
	}

}
