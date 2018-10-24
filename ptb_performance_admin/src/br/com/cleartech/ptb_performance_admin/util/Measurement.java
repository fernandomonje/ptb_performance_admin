package br.com.cleartech.ptb_performance_admin.util;

import java.sql.Timestamp;
import java.util.Date;

public class Measurement {
	
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public float getUploadBandwidth() {
		return uploadBandwidth;
	}
	public void setUploadBandwidth(float uploadBandwidth) {
		this.uploadBandwidth = uploadBandwidth;
	}
	public float getDownloadBandwidth() {
		return downloadBandwidth;
	}
	public void setDownloadBandwidth(float downloadBandwidth) {
		this.downloadBandwidth = downloadBandwidth;
	}
	public float getPingResponseTime() {
		return pingResponseTime;
	}
	public void setPingResponseTime(float pingResponseTime) {
		this.pingResponseTime = pingResponseTime;
	}
	public float getPingPacketLoss() {
		return pingPacketLoss;
	}
	public void setPingPacketLoss(float pingPackageLoss) {
		this.pingPacketLoss = pingPackageLoss;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	private String spid;
	private Timestamp dateTime;
	private float uploadBandwidth;
	private float downloadBandwidth;
	private float pingResponseTime;
	private float pingPacketLoss;
	private String environment;
}
