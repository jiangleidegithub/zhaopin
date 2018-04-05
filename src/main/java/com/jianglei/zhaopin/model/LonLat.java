package com.jianglei.zhaopin.model;


public class LonLat {

	private Double lon;
	private Double lat;

	public LonLat(Double lon, Double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "LonLat [lon=" + lon + ", lat=" + lat + "]";
	}
}