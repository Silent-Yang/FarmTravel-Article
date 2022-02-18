package com.mem.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemJDBCDAO implements MemDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO mem ("
		+ "	mem_acc, mem_pwd, acc_state, mem_name, mem_nickname, "
		+ " mem_mobile, mem_tel, mem_zipcode, mem_city, mem_dist, mem_addr,"
		+ " reg_date, mem_pic, rating_score_mk, rating_count_mk, rating_score_tr,"
		+ " rating_count_tr, report_count, mem_id_state)"
		+ " VALUES"
		+ " (?, ?, ?, ?, ?,"
		+ " ?, ?, ?, ?, ?, ?, "
		+ " ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_id, mem_acc, mem_pwd, acc_state, mem_name, mem_nickname,"
		+ " mem_mobile, mem_tel, mem_zipcode, mem_city, mem_dist, mem_addr,"
		+ "	reg_date, mem_pic, rating_score_mk, rating_count_mk, rating_score_tr,"
		+ " rating_count_tr, report_count, mem_id_state FROM mem ORDER BY mem_id";
//	此insert尚無圖片
	private static final String GET_ONE_STMT = 
		"SELECT mem_id, mem_acc, mem_pwd, acc_state, mem_name, mem_nickname,"
		+ " mem_mobile, mem_tel, mem_zipcode, mem_city, mem_dist, mem_addr,"
		+ " reg_date, mem_pic, rating_score_mk, rating_count_mk, rating_score_tr,"
		+ " rating_count_tr, report_count, mem_id_state FROM mem  WHERE mem_id = ?";
	private static final String DELETE = "DELETE FROM mem WHERE mem_id = ?";
	private static final String UPDATE = 
		"UPDATE mem set mem_acc=?, mem_pwd=?, acc_state=?, mem_name=?, mem_nickname=?,"
		+ " mem_mobile=?, mem_tel=?, mem_zipcode=?, mem_city=?, mem_dist=?, mem_addr=?,"
		+ " reg_date=?, mem_pic=?, rating_score_mk=?, rating_count_mk=?, rating_score_tr=?,"
		+ " rating_count_tr=?, report_count=?, mem_id_state=?  WHERE mem_id = ?";
	private static final String UPDATEMEMINPUT = 
			"UPDATE mem set mem_acc=?, mem_pwd=?, mem_name=?, mem_nickname=?,"
					+ " mem_mobile=?, mem_tel=?, mem_zipcode=?, mem_city=?, mem_dist=?, mem_addr=?,"
					+ " mem_pic=? WHERE mem_id = ?";
	private static final String UPDATEACCSTATE = 
		"UPDATE mem set acc_state=? WHERE mem_id = ?";

	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pwd());
			pstmt.setInt(3, memVO.getAcc_state());
			pstmt.setString(4, memVO.getMem_name());
			pstmt.setString(5, memVO.getMem_nickname());
			pstmt.setString(6, memVO.getMem_mobile());
			pstmt.setString(7, memVO.getMem_tel());
			pstmt.setInt(8, memVO.getMem_zipcode());
			pstmt.setString(9, memVO.getMem_city());
			pstmt.setString(10, memVO.getMem_dist());
			pstmt.setString(11, memVO.getMem_addr());
			pstmt.setDate(12, memVO.getReg_date());
			pstmt.setBytes(13, memVO.getMem_pic());
			pstmt.setInt(14, memVO.getRating_score_mk());
			pstmt.setInt(15, memVO.getRating_count_mk());
			pstmt.setInt(16, memVO.getRating_score_tr());
			pstmt.setInt(17, memVO.getRating_score_mk());
			pstmt.setInt(18, memVO.getReport_count());
			pstmt.setInt(19, memVO.getMem_id_state());

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

	// 無法判斷是否有需要修改的資料
	@Override
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pwd());
			pstmt.setInt(3, memVO.getAcc_state());
			pstmt.setString(4, memVO.getMem_name());
			pstmt.setString(5, memVO.getMem_nickname());
			pstmt.setString(6, memVO.getMem_mobile());
			pstmt.setString(7, memVO.getMem_tel());
			pstmt.setInt(8, memVO.getMem_zipcode());
			pstmt.setString(9, memVO.getMem_city());
			pstmt.setString(10, memVO.getMem_dist());
			pstmt.setString(11, memVO.getMem_addr());
			pstmt.setDate(12, memVO.getReg_date());
			pstmt.setBytes(13, memVO.getMem_pic());
			pstmt.setInt(14, memVO.getRating_score_mk());
			pstmt.setInt(15, memVO.getRating_count_mk());
			pstmt.setInt(16, memVO.getRating_score_tr());
			pstmt.setInt(17, memVO.getRating_score_mk());
			pstmt.setInt(18, memVO.getReport_count());
			pstmt.setInt(19, memVO.getMem_id_state());
			pstmt.setInt(20, memVO.getMem_id());
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
	public void updateMemInput(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEMEMINPUT);
			
			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pwd());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_nickname());
			pstmt.setString(5, memVO.getMem_mobile());
			pstmt.setString(6, memVO.getMem_tel());
			pstmt.setInt(7, memVO.getMem_zipcode());
			pstmt.setString(8, memVO.getMem_city());
			pstmt.setString(9, memVO.getMem_dist());
			pstmt.setString(10, memVO.getMem_addr());
			pstmt.setBytes(11, memVO.getMem_pic());
			pstmt.setInt(12, memVO.getMem_id());
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
	public void updateAccState(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEACCSTATE);

			pstmt.setInt(1, memVO.getAcc_state());
			pstmt.setInt(2, memVO.getMem_id());
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
	public void delete(Integer mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_id);

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
	public MemVO findByPrimaryKey(Integer mem_id) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pwd(rs.getString("mem_pwd"));
				memVO.setAcc_state(rs.getInt("acc_state"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_mobile(rs.getString("mem_mobile"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_zipcode(rs.getInt("mem_zipcode"));
				memVO.setMem_city(rs.getString("mem_city"));
				memVO.setMem_dist(rs.getString("mem_dist"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				memVO.setReg_date(rs.getDate("reg_date"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setRating_score_mk(rs.getInt("rating_score_mk"));
				memVO.setRating_count_mk(rs.getInt("rating_count_mk"));
				memVO.setRating_score_tr(rs.getInt("rating_score_tr"));
				memVO.setRating_count_tr(rs.getInt("rating_count_tr"));
				memVO.setReport_count(rs.getInt("report_count"));
				memVO.setMem_id_state(rs.getInt("mem_id_state"));
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
		return memVO;
	}
	

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

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
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pwd(rs.getString("mem_pwd"));
				memVO.setAcc_state(rs.getInt("acc_state"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_mobile(rs.getString("mem_mobile"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_zipcode(rs.getInt("mem_zipcode"));
				memVO.setMem_city(rs.getString("mem_city"));
				memVO.setMem_dist(rs.getString("mem_dist"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				memVO.setReg_date(rs.getDate("reg_date"));
//				待檢查
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setRating_score_mk(rs.getInt("rating_score_mk"));
				memVO.setRating_count_mk(rs.getInt("rating_count_mk"));
				memVO.setRating_score_tr(rs.getInt("rating_score_tr"));
				memVO.setRating_count_tr(rs.getInt("rating_count_tr"));
				memVO.setReport_count(rs.getInt("report_count"));
				memVO.setMem_id_state(rs.getInt("mem_id_state"));
				list.add(memVO); // Store the row in the list
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
	public List<MemVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
////	 Handle with byte array data 此區尚未用到，可能會移除
//	public static void readPicture(byte[] bytes) throws IOException {
//		FileOutputStream fos = new FileOutputStream("Output/2.png");
//		fos.write(bytes);
//		fos.flush();
//		fos.close();
//	}
}