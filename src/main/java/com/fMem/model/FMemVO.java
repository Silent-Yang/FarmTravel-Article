package com.fMem.model;

import java.sql.Date;

public class FMemVO implements java.io.Serializable {
	private Integer f_mem_id;
	private Integer mem_id;
	private String f_mem_acc;
	private String f_mem_pwd;
	private Integer acc_state;
	private String f_mem_fname;
	private String f_mem_info;
	private String f_mem_mobile;
	private String f_mem_tel;
	private Integer f_mem_zipcode;
	private String f_mem_city;
	private String f_mem_dist;
	private String f_mem_add;
	private Integer bank_code;
	private String bank_account;
	private Date reg_date;
	private byte[] f_mem_pic;
	private Integer rating_score_mk;
	private Integer rating_count_mk;
	private Integer rating_score_tr;
	private Integer rating_count_tr;
	private Integer report_count;
	private byte[] organic_certi;
	private byte[] env_friendly_certi;
	private Integer certi_state;
	
	public Integer getF_mem_id() {
		return f_mem_id;
	}
	public void setF_mem_id(Integer f_mem_id) {
		this.f_mem_id = f_mem_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getF_mem_acc() {
		return f_mem_acc;
	}
	public void setF_mem_acc(String f_mem_acc) {
		this.f_mem_acc = f_mem_acc;
	}
	public String getF_mem_pwd() {
		return f_mem_pwd;
	}
	public void setF_mem_pwd(String f_mem_pwd) {
		this.f_mem_pwd = f_mem_pwd;
	}
	public Integer getAcc_state() {
		return acc_state;
	}
	public void setAcc_state(Integer acc_state) {
		this.acc_state = acc_state;
	}
	public String getF_mem_fname() {
		return f_mem_fname;
	}
	public void setF_mem_fname(String f_mem_fname) {
		this.f_mem_fname = f_mem_fname;
	}
	public String getF_mem_info() {
		return f_mem_info;
	}
	public void setF_mem_info(String f_mem_info) {
		this.f_mem_info = f_mem_info;
	}
	public String getF_mem_mobile() {
		return f_mem_mobile;
	}
	public void setF_mem_mobile(String f_mem_mobile) {
		this.f_mem_mobile = f_mem_mobile;
	}
	public String getF_mem_tel() {
		return f_mem_tel;
	}
	public void setF_mem_tel(String f_mem_tel) {
		this.f_mem_tel = f_mem_tel;
	}
	public Integer getF_mem_zipcode() {
		return f_mem_zipcode;
	}
	public void setF_mem_zipcode(Integer f_mem_zipcode) {
		this.f_mem_zipcode = f_mem_zipcode;
	}
	public String getF_mem_city() {
		return f_mem_city;
	}
	public void setF_mem_city(String f_mem_city) {
		this.f_mem_city = f_mem_city;
	}
	public String getF_mem_dist() {
		return f_mem_dist;
	}
	public void setF_mem_dist(String f_mem_dist) {
		this.f_mem_dist = f_mem_dist;
	}
	public String getF_mem_add() {
		return f_mem_add;
	}
	public void setF_mem_add(String f_mem_add) {
		this.f_mem_add = f_mem_add;
	}
	public Integer getBank_code() {
		return bank_code;
	}
	public void setBank_code(Integer bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public byte[] getF_mem_pic() {
		return f_mem_pic;
	}
	public void setF_mem_pic(byte[] f_mem_pic) {
		this.f_mem_pic = f_mem_pic;
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
	public byte[] getOrganic_certi() {
		return organic_certi;
	}
	public void setOrganic_certi(byte[] organic_certi) {
		this.organic_certi = organic_certi;
	}
	public byte[] getEnv_friendly_certi() {
		return env_friendly_certi;
	}
	public void setEnv_friendly_certi(byte[] env_friendly_certi) {
		this.env_friendly_certi = env_friendly_certi;
	}
	public Integer getCerti_state() {
		return certi_state;
	}
	public void setCerti_state(Integer certi_state) {
		this.certi_state = certi_state;
	}
}
