package com.mem.model;

import java.sql.Date;

public class MemVO implements java.io.Serializable {
	private Integer mem_id;
	private String mem_acc;
	private String mem_pwd;
	private Integer acc_state;
	private String mem_name;
	private String mem_nickname;
	private String mem_mobile;
	private String mem_tel;
	private Integer mem_zipcode;
	private String mem_city;
	private String mem_dist;
	private String mem_addr;
	private Date reg_date;
	private byte[] mem_pic;
	private Integer rating_score_mk;
	private Integer rating_count_mk;
	private Integer rating_score_tr;
	private Integer rating_count_tr;
	private Integer report_count;
	private Integer mem_id_state;

	public Integer getMem_id() {
		return mem_id;
	}

	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_acc() {
		return mem_acc;
	}

	public void setMem_acc(String mem_acc) {
		this.mem_acc = mem_acc;
	}

	public String getMem_pwd() {
		return mem_pwd;
	}

	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	public Integer getAcc_state() {
		return acc_state;
	}

	public void setAcc_state(Integer acc_state) {
		this.acc_state = acc_state;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public Integer getMem_zipcode() {
		return mem_zipcode;
	}

	public void setMem_zipcode(Integer mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}

	public String getMem_city() {
		return mem_city;
	}

	public void setMem_city(String mem_city) {
		this.mem_city = mem_city;
	}

	public String getMem_dist() {
		return mem_dist;
	}

	public void setMem_dist(String mem_dist) {
		this.mem_dist = mem_dist;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public byte[] getMem_pic() {
		return mem_pic;
	}

	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}

	public Integer getRating_score_mk() {
		return rating_score_mk;
	}

	public void setRating_score_mk(Integer rating_score_mk) {
		this.rating_score_mk = rating_score_mk;
	}

	public Integer getRating_count_mk() {
		return rating_count_mk;
	}

	public void setRating_count_mk(Integer rating_count_mk) {
		this.rating_count_mk = rating_count_mk;
	}

	public Integer getRating_score_tr() {
		return rating_score_tr;
	}

	public void setRating_score_tr(Integer rating_score_tr) {
		this.rating_score_tr = rating_score_tr;
	}

	public Integer getRating_count_tr() {
		return rating_count_tr;
	}

	public void setRating_count_tr(Integer rating_count_tr) {
		this.rating_count_tr = rating_count_tr;
	}

	public Integer getReport_count() {
		return report_count;
	}

	public void setReport_count(Integer report_count) {
		this.report_count = report_count;
	}

	public Integer getMem_id_state() {
		return mem_id_state;
	}

	public void setMem_id_state(Integer mem_id_state) {
		this.mem_id_state = mem_id_state;
	}
	
}
