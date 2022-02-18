package com.mem.model;

import java.sql.Date;
import java.util.List;




public class MemService {
	private MemDAO_interface dao;

	public MemService() {
		dao = new MemJDBCDAO();
	}

	public MemVO addMem(String mem_acc, String mem_pwd, Integer acc_state, String mem_name, 
						String mem_nickname, String mem_mobile, String mem_tel, Integer mem_zipcode,
						String mem_city, String mem_dist, String mem_addr, Date reg_date, 
						byte[] mem_pic, Integer rating_score_mk, Integer rating_count_mk, Integer rating_score_tr,
						Integer rating_count_tr, Integer report_count, Integer mem_id_state) {

		MemVO memVO = new MemVO();

		memVO.setMem_acc(mem_acc);
		memVO.setMem_pwd(mem_pwd);
		memVO.setAcc_state(acc_state);
		memVO.setMem_name(mem_name);
		memVO.setMem_nickname(mem_nickname);
		memVO.setMem_mobile(mem_mobile);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_zipcode(mem_zipcode);
		memVO.setMem_city(mem_city);
		memVO.setMem_dist(mem_dist);
		memVO.setMem_addr(mem_addr);
		memVO.setReg_date(reg_date);
		memVO.setMem_pic(mem_pic);
		memVO.setRating_score_mk(rating_score_mk);
		memVO.setRating_count_mk(rating_count_mk);
		memVO.setRating_score_tr(rating_score_tr);
		memVO.setRating_count_tr(rating_count_tr);
		memVO.setReport_count(report_count);
		memVO.setMem_id_state(mem_id_state);
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(Integer mem_id, String mem_acc, String mem_pwd, Integer acc_state, String mem_name, 
			String mem_nickname, String mem_mobile, String mem_tel, Integer mem_zipcode,
			String mem_city, String mem_dist, String mem_addr, Date reg_date, 
			byte[] mem_pic, Integer rating_score_mk, Integer rating_count_mk, Integer rating_score_tr,
			Integer rating_count_tr, Integer report_count, Integer mem_id_state) {

		MemVO memVO = new MemVO();
		
		memVO.setMem_id(mem_id);
		memVO.setMem_acc(mem_acc);
		memVO.setMem_pwd(mem_pwd);
		memVO.setAcc_state(acc_state);
		memVO.setMem_name(mem_name);
		memVO.setMem_nickname(mem_nickname);
		memVO.setMem_mobile(mem_mobile);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_zipcode(mem_zipcode);
		memVO.setMem_city(mem_city);
		memVO.setMem_dist(mem_dist);
		memVO.setMem_addr(mem_addr);
		memVO.setReg_date(reg_date);
		memVO.setMem_pic(mem_pic);
		memVO.setRating_score_mk(rating_score_mk);
		memVO.setRating_count_mk(rating_count_mk);
		memVO.setRating_score_tr(rating_score_tr);
		memVO.setRating_count_tr(rating_count_tr);
		memVO.setReport_count(report_count);
		memVO.setMem_id_state(mem_id_state);
		dao.update(memVO);

		return memVO;
	}
	//修改會員資料
	public MemVO updateMemInput(Integer mem_id, String mem_acc, String mem_pwd, String mem_name, 
			String mem_nickname, String mem_mobile, String mem_tel, Integer mem_zipcode,
			String mem_city, String mem_dist, String mem_addr, byte[] mem_pic) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_id(mem_id);
		memVO.setMem_acc(mem_acc);
		memVO.setMem_pwd(mem_pwd);
		memVO.setMem_name(mem_name);
		memVO.setMem_nickname(mem_nickname);
		memVO.setMem_mobile(mem_mobile);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_zipcode(mem_zipcode);
		memVO.setMem_city(mem_city);
		memVO.setMem_dist(mem_dist);
		memVO.setMem_addr(mem_addr);
		memVO.setMem_pic(mem_pic);
		dao.updateMemInput(memVO);
		
		return memVO;
	}
	// 專門修改帳號狀態 待確認
	public MemVO updateMemAccState(Integer mem_id, Integer acc_state) {

		MemVO memVO = new MemVO();
		memVO.setMem_id(mem_id);
		memVO.setAcc_state(acc_state);
		dao.updateAccState(memVO);
		return memVO;
	}

	public void deleteMem(Integer mem_id) {
		dao.delete(mem_id);
	}

	public MemVO getOneMem(Integer mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
}
