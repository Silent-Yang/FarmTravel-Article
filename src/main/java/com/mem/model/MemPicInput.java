package com.mem.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 不完整版,無法判斷讀到哪裡，僅用於新增會員用
public class MemPicInput {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA104G2?serverTimezone=Asia/Taipei";
	public static final String USERID = "root";
	public static final String PASSWD = "password";

	private static final String SQL = "UPDATE mem set mem_pic = ? where mem_id = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(SQL);
			int i = 0;
			for (int j = 77000; j <= 77007; j++) {
				byte[] pic = getPictureByteArray("MemPicInput/mempic" + ++i + ".jpg");
				pstmt.setBytes(1, pic);
				pstmt.setInt(2, j);
				pstmt.executeUpdate();
				System.out.println("新增 " + i + ".jpg 成功");
			}

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// 使用InputStream資料流方式
	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()]; // available():資料流有多少bytes資料，算出有多少bytes
		fis.read(buffer); // 讀取多少bytes資料存入buffer
		fis.close();
		return buffer;
	}
}
