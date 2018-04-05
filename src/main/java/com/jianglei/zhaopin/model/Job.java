package com.jianglei.zhaopin.model;

public class Job {
	private Integer jobId;

	private String jobName;

	private String jobTag;

	private String jobSalary;

	private String jobPosition;

	private Integer jobProperty;

	private String jobExperience;

	private String jobMinDegree;

	private String jobDescription;

	private String jobUrl;

	private String jobAddress;

	private Double jobLon;

	private Double jobLat;

	private Integer companyId;
	
	private Company company;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobTag() {
		return jobTag;
	}

	public void setJobTag(String jobTag) {
		this.jobTag = jobTag;
	}

	public String getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public Integer getJobProperty() {
		return jobProperty;
	}

	public void setJobProperty(Integer jobProperty) {
		this.jobProperty = jobProperty;
	}

	public String getJobExperience() {
		return jobExperience;
	}

	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}

	public String getJobMinDegree() {
		return jobMinDegree;
	}

	public void setJobMinDegree(String jobMinDegree) {
		this.jobMinDegree = jobMinDegree;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobUrl() {
		return jobUrl;
	}

	public void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public Double getJobLon() {
		return jobLon;
	}

	public void setJobLon(Double jobLon) {
		this.jobLon = jobLon;
	}

	public Double getJobLat() {
		return jobLat;
	}

	public void setJobLat(Double jobLat) {
		this.jobLat = jobLat;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobTag=" + jobTag + ", jobSalary=" + jobSalary
				+ ", jobPosition=" + jobPosition + ", jobProperty=" + jobProperty + ", jobExperience=" + jobExperience
				+ ", jobMinDegree=" + jobMinDegree + ", jobDescription=" + jobDescription + ", jobUrl=" + jobUrl
				+ ", jobAddress=" + jobAddress + ", jobLon=" + jobLon + ", jobLat=" + jobLat + ", companyId="
				+ companyId + ", company=" + company + "]";
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}