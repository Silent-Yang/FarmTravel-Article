package com.fMem.model;

import java.sql.Date;
import java.util.List;

public class FMemService {
	private FMemDAO_interface dao;

	public FMemService() {
		dao = new FMemJDBCDAO();
	}

	public FMemVO addFMem(Integer mem_id, String f_mem_acc, String f_mem_pwd, String f_mem_fname,
			String f_mem_mobile, Integer f_mem_zipcode, String f_mem_city, 
			String f_mem_dist, String f_mem_add ) {

		FMemVO fMemVO = new FMemVO();

		fMemVO.setMem_id(mem_id);
		fMemVO.setF_mem_acc(f_mem_acc);
		fMemVO.setF_mem_pwd(f_mem_pwd);
		fMemVO.setF_mem_fname(f_mem_fname);
		fMemVO.setF_mem_mobile(f_mem_mobile);
		fMemVO.setF_mem_zipcode(f_mem_zipcode);
		fMemVO.setF_mem_city(f_mem_city);
		fMemVO.setF_mem_dist(f_mem_dist);
		fMemVO.setF_mem_add(f_mem_add);
		dao.insert(fMemVO);

		return fMemVO;
	}
	
	public FMemVO addAllFMem(Integer mem_id, String f_mem_acc, String f_mem_pwd, Integer acc_state, String f_mem_fname,
			String f_mem_info, String f_mem_mobile, String f_mem_tel, Integer f_mem_zipcode, String f_mem_city, 
			String f_mem_dist, String f_mem_add, Integer bank_code, String bank_account, Date reg_date, byte[] f_mem_pic,
			Integer rating_score_mk, Integer rating_count_mk, Integer rating_score_tr, Integer rating_count_tr,
			Integer report_count, byte[] organic_certi, byte[] env_friendly_certi, Integer certi_state) {
		
		FMemVO fMemVO = new FMemVO();
		
		fMemVO.setMem_id(mem_id);
		fMemVO.setF_mem_acc(f_mem_acc);
		fMemVO.setF_mem_pwd(f_mem_pwd);
		fMemVO.setAcc_state(acc_state);
		fMemVO.setF_mem_fname(f_mem_fname);
		fMemVO.setF_mem_info(f_mem_info);
		fMemVO.setF_mem_mobile(f_mem_mobile);
		fMemVO.setF_mem_tel(f_mem_tel);
		fMemVO.setF_mem_zipcode(f_mem_zipcode);
		fMemVO.setF_mem_city(f_mem_city);
		fMemVO.setF_mem_dist(f_mem_dist);
		fMemVO.setF_mem_add(f_mem_add);
		fMemVO.setBank_code(bank_code);
		fMemVO.setBank_account(bank_account);
		fMemVO.setReg_date(reg_date);
		fMemVO.setF_mem_pic(f_mem_pic);
		fMemVO.setRating_score_mk(rating_score_mk);
		fMemVO.setRating_count_mk(rating_count_mk);
		fMemVO.setRating_score_tr(rating_score_tr);
		fMemVO.setRating_count_tr(rating_count_tr);
		fMemVO.setReport_count(report_count);
		fMemVO.setOrganic_certi(organic_certi);
		fMemVO.setEnv_friendly_certi(env_friendly_certi);
		fMemVO.setCerti_state(certi_state);
		dao.insertAll(fMemVO);// 未使用且未完成
		
		return fMemVO;
	}

	public FMemVO updateFMem(Integer f_mem_id, Integer mem_id, String f_mem_acc, String f_mem_pwd, Integer acc_state, String f_mem_fname,
			String f_mem_info, String f_mem_mobile, String f_mem_tel, Integer f_mem_zipcode, String f_mem_city, 
			String f_mem_dist, String f_mem_add, Integer bank_code, String bank_account, Date reg_date, byte[] f_mem_pic,
			Integer rating_score_mk, Integer rating_count_mk, Integer rating_score_tr, Integer rating_count_tr,
			Integer report_count, byte[] organic_certi, byte[] env_friendly_certi, Integer certi_state) {

		FMemVO fMemVO = new FMemVO();

		fMemVO.setF_mem_id(f_mem_id);
		fMemVO.setMem_id(mem_id);
		fMemVO.setF_mem_acc(f_mem_acc);
		fMemVO.setF_mem_pwd(f_mem_pwd);
		fMemVO.setAcc_state(acc_state);
		fMemVO.setF_mem_fname(f_mem_fname);
		fMemVO.setF_mem_info(f_mem_info);
		fMemVO.setF_mem_mobile(f_mem_mobile);
		fMemVO.setF_mem_tel(f_mem_tel);
		fMemVO.setF_mem_zipcode(f_mem_zipcode);
		fMemVO.setF_mem_city(f_mem_city);
		fMemVO.setF_mem_dist(f_mem_dist);
		fMemVO.setF_mem_add(f_mem_add);
		fMemVO.setBank_code(bank_code);
		fMemVO.setBank_account(bank_account);
		fMemVO.setReg_date(reg_date);
		fMemVO.setF_mem_pic(f_mem_pic);
		fMemVO.setRating_score_mk(rating_score_mk);
		fMemVO.setRating_count_mk(rating_count_mk);
		fMemVO.setRating_score_tr(rating_score_tr);
		fMemVO.setRating_count_tr(rating_count_tr);
		fMemVO.setReport_count(report_count);
		fMemVO.setOrganic_certi(organic_certi);
		fMemVO.setEnv_friendly_certi(env_friendly_certi);
		fMemVO.setCerti_state(certi_state);
		dao.update(fMemVO);

		return fMemVO;
	}

	public void deleteFMem(Integer f_mem_id) {
		dao.delete(f_mem_id);
	}

	public FMemVO getOneFMem(Integer f_mem_id) {
		return dao.findByPrimaryKey(f_mem_id);
	}

	public List<FMemVO> getAll() {
		return dao.getAll();
	}
}
