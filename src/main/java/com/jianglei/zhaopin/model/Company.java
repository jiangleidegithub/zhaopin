package com.jianglei.zhaopin.model;

import java.util.List;

public class Company {
    private Integer companyId;

    private String companyName;

    private String companyProperty;

    private String companySize;

    private String companyIndustry;

    private String companyAddress;

    private Double companyLon;

    private Double companyLat;

    private String companyDescription;

    private String companyUrl;
    
    private List<Job> jobList;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyProperty() {
        return companyProperty;
    }

    public void setCompanyProperty(String companyProperty) {
        this.companyProperty = companyProperty;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Double getCompanyLon() {
        return companyLon;
    }

    public void setCompanyLon(Double companyLon) {
        this.companyLon = companyLon;
    }

    public Double getCompanyLat() {
        return companyLat;
    }

    public void setCompanyLat(Double companyLat) {
        this.companyLat = companyLat;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyProperty="
				+ companyProperty + ", companySize=" + companySize + ", companyIndustry=" + companyIndustry
				+ ", companyAddress=" + companyAddress + ", companyLon=" + companyLon + ", companyLat=" + companyLat
				+ ", companyDescription=" + companyDescription + ", companyUrl=" + companyUrl + ", jobList=" + jobList
				+ "]";
	}
}