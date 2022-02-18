package com.fMem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FMemJDBCDAO implements FMemDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO f_mem ("
			+ "	mem_id, f_mem_acc, f_mem_pwd, f_mem_fname, "
			+ " f_mem_mobile, f_mem_zipcode, f_mem_city, "
			+ "f_mem_dist, f_mem_add)" 
			+ " VALUES" 
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT_All = "INSERT INTO f_mem ("
			+ "	mem_id, f_mem_acc, f_mem_pwd, acc_state, f_mem_fname, "
			+ " f_mem_info, f_mem_mobile, f_mem_tel, f_mem_zipcode, f_mem_city, "
			+ "f_mem_dist, f_mem_add, bank_code, bank_account, reg_date, f_mem_pic,"
			+ " rating_score_mk, rating_count_mk, rating_score_tr,rating_count_tr, "
			+ "report_count, organic_certi, env_friendly_certi, certi_state)" 
			+ " VALUES" 
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT f_mem_id, mem_id, f_mem_acc, f_mem_pwd, acc_state, f_mem_fname, "
			+ " f_mem_info, f_mem_mobile, f_mem_tel, f_mem_zipcode, f_mem_city, "
			+ " f_mem_dist, f_mem_add, bank_code, bank_account, reg_date, f_mem_pic, "
			+ " rating_score_mk, rating_count_mk, rating_score_tr,rating_count_tr, "
			+ " report_count, organic_certi, env_friendly_certi, certi_state "
			+ " FROM f_mem ORDER BY f_mem_id";
	private static final String GET_ONE_STMT = 
			"SELECT f_mem_id, mem_id, f_mem_acc, f_mem_pwd, acc_state, f_mem_fname, "
			+ " f_mem_info, f_mem_mobile, f_mem_tel, f_mem_zipcode, f_mem_city, "
			+ " f_mem_dist, f_mem_add, bank_code, bank_account, reg_date, f_mem_pic, "
			+ " rating_score_mk, rating_count_mk, rating_score_tr,rating_count_tr, "
			+ " report_count, organic_certi, env_friendly_certi, certi_state "
			+ " FROM f_mem WHERE f_mem_id = ?";
	private static final String DELETE = "DELETE FROM f_mem WHERE f_mem_id = ?";
	private static final String UPDATE = 
			"UPDATE f_mem set mem_id=?, f_mem_acc=?, f_mem_pwd=?, acc_state=?, f_mem_fname=?, "
			+ " f_mem_info=?, f_mem_mobile=?, f_mem_tel=?, f_mem_zipcode=?, f_mem_city=?, "
			+ " f_mem_dist=?, f_mem_add=?, bank_code=?, bank_account=?, reg_date=?, f_mem_pic=?, "
			+ " rating_score_mk=?, rating_count_mk=?, rating_score_tr=?, rating_count_tr=?, "
			+ " report_count=?, organic_certi=?, env_friendly_certi=?, certi_state=? WHERE f_mem_id = ?";

	
	@Override
	public void insert(FMemVO fMemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, fMemVO.getMem_id());
			pstmt.setString(2, fMemVO.getF_mem_acc());
			pstmt.setString(3, fMemVO.getF_mem_pwd());
			pstmt.setString(4, fMemVO.getF_mem_fname());
			pstmt.setString(5, fMemVO.getF_mem_mobile());
			pstmt.setInt(6, fMemVO.getF_mem_zipcode());
			pstmt.setString(7, fMemVO.getF_mem_city());
			pstmt.setString(8, fMemVO.getF_mem_dist());
			pstmt.setString(9, fMemVO.getF_mem_add());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void insertAll(FMemVO fMemVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT_All);
			
			pstmt.setInt(1, fMemVO.getMem_id());
			pstmt.setString(2, fMemVO.getF_mem_acc());
			pstmt.setString(3, fMemVO.getF_mem_pwd());
			pstmt.setInt(4, fMemVO.getAcc_state());
			pstmt.setString(5, fMemVO.getF_mem_fname());
			pstmt.setString(6, fMemVO.getF_mem_info());
			pstmt.setString(7, fMemVO.getF_mem_mobile());
			pstmt.setString(8, fMemVO.getF_mem_tel());
			pstmt.setInt(9, fMemVO.getF_mem_zipcode());
			pstmt.setString(10, fMemVO.getF_mem_city());
			pstmt.setString(11, fMemVO.getF_mem_dist());
			pstmt.setString(12, fMemVO.getF_mem_add());
			pstmt.setInt(13, fMemVO.getBank_code());
			pstmt.setString(14, fMemVO.getBank_account());
			pstmt.setDate(15, fMemVO.getReg_date());
			pstmt.setBytes(16, fMemVO.getF_mem_pic());
			pstmt.setInt(17, fMemVO.getRating_score_mk());
			pstmt.setInt(18, fMemVO.getRating_count_mk());
			pstmt.setInt(19, fMemVO.getRating_score_tr());
			pstmt.setInt(20, fMemVO.getRating_count_tr());
			pstmt.setInt(21, fMemVO.getReport_count());
			pstmt.setBytes(22, fMemVO.getOrganic_certi());
			pstmt.setBytes(23, fMemVO.getEnv_friendly_certi());
			pstmt.setInt(24, fMemVO.getCerti_state());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	
	@Override
	public void update(FMemVO fMemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, fMemVO.getMem_id());
			pstmt.setString(2, fMemVO.getF_mem_acc());
			pstmt.setString(3, fMemVO.getF_mem_pwd());
			pstmt.setInt(4, fMemVO.getAcc_state());
			pstmt.setString(5, fMemVO.getF_mem_fname());
			pstmt.setString(6, fMemVO.getF_mem_info());
			pstmt.setString(7, fMemVO.getF_mem_mobile());
			pstmt.setString(8, fMemVO.getF_mem_tel());
			pstmt.setInt(9, fMemVO.getF_mem_zipcode());
			pstmt.setString(10, fMemVO.getF_mem_city());
			pstmt.setString(11, fMemVO.getF_mem_dist());
			pstmt.setString(12, fMemVO.getF_mem_add());
			pstmt.setInt(13, fMemVO.getBank_code());
			pstmt.setString(14, fMemVO.getBank_account());
			pstmt.setDate(15, fMemVO.getReg_date());
			pstmt.setBytes(16, fMemVO.getF_mem_pic());
			pstmt.setInt(17, fMemVO.getRating_score_mk());
			pstmt.setInt(18, fMemVO.getRating_count_mk());
			pstmt.setInt(19, fMemVO.getRating_score_tr());
			pstmt.setInt(20, fMemVO.getRating_count_tr());
			pstmt.setInt(21, fMemVO.getReport_count());
			pstmt.setBytes(22, fMemVO.getOrganic_certi());
			pstmt.setBytes(23, fMemVO.getEnv_friendly_certi());
			pstmt.setInt(24, fMemVO.getCerti_state());
			pstmt.setInt(25, fMemVO.getF_mem_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}


	@Override
	public void delete(Integer f_mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, f_mem_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	@Override
	public FMemVO findByPrimaryKey(Integer f_mem_id) {

		FMemVO fMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, f_mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				fMemVO = new FMemVO();
				fMemVO.setF_mem_id(rs.getInt("f_mem_id"));
				fMemVO.setMem_id(rs.getInt("mem_id"));
				fMemVO.setF_mem_acc(rs.getString("f_mem_acc"));
				fMemVO.setF_mem_pwd(rs.getString("f_mem_pwd"));
				fMemVO.setAcc_state(rs.getInt("acc_state"));
				fMemVO.setF_mem_fname(rs.getString("f_mem_fname"));
				fMemVO.setF_mem_info(rs.getString("f_mem_info"));
				fMemVO.setF_mem_mobile(rs.getString("f_mem_mobile"));
				fMemVO.setF_mem_tel(rs.getString("f_mem_tel"));
				fMemVO.setF_mem_zipcode(rs.getInt("f_mem_zipcode"));
				fMemVO.setF_mem_city(rs.getString("f_mem_city"));
				fMemVO.setF_mem_dist(rs.getString("f_mem_dist"));
				fMemVO.setF_mem_add(rs.getString("f_mem_add"));
				fMemVO.setBank_code(rs.getInt("bank_code"));
				fMemVO.setBank_account(rs.getString("bank_account"));
				fMemVO.setReg_date(rs.getDate("reg_date"));
				fMemVO.setF_mem_pic(rs.getBytes("f_mem_pic"));
				fMemVO.setRating_score_mk(rs.getInt("rating_score_mk"));
				fMemVO.setRating_count_mk(rs.getInt("rating_count_mk"));
				fMemVO.setRating_score_tr(rs.getInt("rating_score_tr"));
				fMemVO.setRating_count_tr(rs.getInt("rating_count_tr"));
				fMemVO.setReport_count(rs.getInt("report_count"));
				fMemVO.setOrganic_certi(rs.getBytes("organic_certi"));
				fMemVO.setEnv_friendly_certi(rs.getBytes("env_friendly_certi"));
				fMemVO.setCerti_state(rs.getInt("certi_state"));
				}	
				

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return fMemVO;
	}
	
	@Override
	public List<FMemVO> getAll() {
		List<FMemVO> list = new ArrayList<FMemVO>();
		FMemVO fMemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				fMemVO = new FMemVO();
				fMemVO = new FMemVO();
				fMemVO.setF_mem_id(rs.getInt("f_mem_id"));
				fMemVO.setMem_id(rs.getInt("mem_id"));
				fMemVO.setF_mem_acc(rs.getString("f_mem_acc"));
				fMemVO.setF_mem_pwd(rs.getString("f_mem_pwd"));
				fMemVO.setAcc_state(rs.getInt("acc_state"));
				fMemVO.setF_mem_fname(rs.getString("f_mem_fname"));
				fMemVO.setF_mem_info(rs.getString("f_mem_info"));
				fMemVO.setF_mem_mobile(rs.getString("f_mem_mobile"));
				fMemVO.setF_mem_tel(rs.getString("f_mem_tel"));
				fMemVO.setF_mem_zipcode(rs.getInt("f_mem_zipcode"));
				fMemVO.setF_mem_city(rs.getString("f_mem_city"));
				fMemVO.setF_mem_dist(rs.getString("f_mem_dist"));
				fMemVO.setF_mem_add(rs.getString("f_mem_add"));
				fMemVO.setBank_code(rs.getInt("bank_code"));
				fMemVO.setBank_account(rs.getString("bank_account"));
				fMemVO.setReg_date(rs.getDate("reg_date"));
				fMemVO.setF_mem_pic(rs.getBytes("f_mem_pic"));
				fMemVO.setRating_score_mk(rs.getInt("rating_score_mk"));
				fMemVO.setRating_count_mk(rs.getInt("rating_count_mk"));
				fMemVO.setRating_score_tr(rs.getInt("rating_score_tr"));
				fMemVO.setRating_count_tr(rs.getInt("rating_count_tr"));
				fMemVO.setReport_count(rs.getInt("report_count"));
				fMemVO.setOrganic_certi(rs.getBytes("organic_certi"));
				fMemVO.setEnv_friendly_certi(rs.getBytes("env_friendly_certi"));
				fMemVO.setCerti_state(rs.getInt("certi_state"));
				list.add(fMemVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<FMemVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
 	