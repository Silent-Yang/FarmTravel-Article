package com.fMem.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fMem.model.FMemService;
import com.fMem.model.FMemVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
@MultipartConfig
// 前後台的mem可能需要分開寫servlet，可能解決方法:分別註冊back-end與front-end的url
public class FMemServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				FMemVO fMemVO = new FMemVO();
Integer mem_id = new Integer(req.getParameter("mem_id").trim());

String f_mem_acc = req.getParameter("f_mem_acc");
				String fMemAccReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})?$";
				if (f_mem_acc == null || f_mem_acc.trim().length() == 0) {
					errorMsgs.add("小農帳號: 請勿空白");
				} else if(!f_mem_acc.trim().matches(fMemAccReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("不符合帳號格式，請輸入正確e-mail");
	            }
				
String f_mem_pwd = req.getParameter("f_mem_pwd").trim();
				String fMemReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
				if (f_mem_pwd == null || f_mem_pwd.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!f_mem_pwd.trim().matches(fMemReg)) {
					errorMsgs.add("至少8個字符，至少1個大寫字母，1個小寫字母和1個數字");
				}
				
String f_mem_fname = req.getParameter("f_mem_fname").trim();
				if (f_mem_fname == null || f_mem_fname.trim().length() == 0) {
					errorMsgs.add("農場名稱請勿空白");
				}
				
				String f_mem_mobile = req.getParameter("f_mem_mobile").trim();
				String mobileReg = "^09[0-9]{8}$";
				if (f_mem_mobile == null || f_mem_mobile.trim().length() == 0) {
					errorMsgs.add("請填入手機號碼.");
				} else if (!f_mem_mobile.trim().matches(mobileReg)) {
					errorMsgs.add("請輸入正確的手機號碼格式");
				}
				
				Integer f_mem_zipcode = new Integer(req.getParameter("f_mem_zipcode").trim());
				
				String f_mem_city = req.getParameter("f_mem_city").trim();
				if (f_mem_city == null || f_mem_city.trim().length() == 0) {
					errorMsgs.add("縣市請勿空白");
				}

				String f_mem_dist = req.getParameter("f_mem_dist").trim();
				if (f_mem_dist == null || f_mem_dist.trim().length() == 0) {
					errorMsgs.add("鄉鎮區請勿空白");
				} 

				String f_mem_add = req.getParameter("f_mem_add").trim();
				if (f_mem_add == null || f_mem_add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				} 
				
				
//				Part part = req.getPart("f_mem_pic");
//				
//				byte[] f_mem_pic = null;
//				if (part == null || part.getSize() == 0) {
//				} else {
//					req.setAttribute("fMemVO", fMemVO);
//					InputStream in = part.getInputStream();
//					f_mem_pic = new byte[in.available()];
//					in.read(f_mem_pic);
//					in.close();
//				}
//				
//				Part part2 = req.getPart("organic_certi");
//
//				byte[] organic_certi = null;
//				if (part2 == null || part2.getSize() == 0) {
//				} else {
//					req.setAttribute("fMemVO", fMemVO);
//					InputStream in = part.getInputStream();
//					organic_certi = new byte[in.available()];
//					in.read(organic_certi);
//					in.close();
//				}
//				
//				Part part3 = req.getPart("env_friendly_certi");
//
//				byte[] env_friendly_certi = null;
//				if (part3 == null || part3.getSize() == 0) {
//				} else {
//					req.setAttribute("fMemVO", fMemVO);
//					InputStream in = part.getInputStream();
//					env_friendly_certi = new byte[in.available()];
//					in.read(env_friendly_certi);
//					in.close();
//				}
				

				fMemVO.setMem_id(mem_id);
				fMemVO.setF_mem_acc(f_mem_acc);
				fMemVO.setF_mem_pwd(f_mem_pwd);
				fMemVO.setF_mem_fname(f_mem_fname);
				fMemVO.setF_mem_mobile(f_mem_mobile);
				fMemVO.setF_mem_zipcode(f_mem_zipcode);
				fMemVO.setF_mem_city(f_mem_city);
				fMemVO.setF_mem_dist(f_mem_dist);
				fMemVO.setF_mem_add(f_mem_add);
//				fMemVO.setF_mem_pic(f_mem_pic);
//				fMemVO.setOrganic_certi(organic_certi);
//				fMemVO.setEnv_friendly_certi(env_friendly_certi);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("fMemVO", fMemVO); // 含有輸入格式錯誤的fMemVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/fMem/addFMem.jsp");//尚未更改
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FMemService fMemSvc = new FMemService();
				fMemVO = fMemSvc.addFMem(mem_id, f_mem_acc, f_mem_pwd, f_mem_fname, 
						f_mem_mobile, f_mem_zipcode, f_mem_city, f_mem_dist, f_mem_add);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/fMem/addFMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/fMem/addFMem.jsp");
//				failureView.forward(req, res);
//			}
		}
		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
