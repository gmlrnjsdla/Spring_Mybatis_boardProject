package com.heekwoncompany.myfreeboard.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heekwoncompany.myfreeboard.dao.mapper.IDao;
import com.heekwoncompany.myfreeboard.dto.MemberDto;




@Controller
public class FBoardController {
	
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "joinMember")
	public String joinMember() {
		
		return "joinMember";
	}
	
	@RequestMapping(value = "joinOk", method = RequestMethod.POST)
	public String joinOk(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		int checkIdFlag = dao.checkIdDao(mid);
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		if(checkIdFlag == 0) {
			dao.joinMemberDao(mid, mpw, mname, memail);
			model.addAttribute("mname", mname);
		}
		return "joinOk";
	}
	
	@RequestMapping(value = "checkId")
	public String checkId(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String checkId = request.getParameter("checkId");
		int checkIdFlag = dao.checkIdDao(checkId);
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		return "checkId";
	}
	
	@RequestMapping(value = "login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value = "loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		int checkIdFlag = dao.checkIdDao(mid);
		int checkPwFlag = dao.checkPwDao(mid, mpw);
		
		
		
		model.addAttribute("mid",mid);
		model.addAttribute("checkIdFlag", checkIdFlag);
		model.addAttribute("checkPwFlag", checkPwFlag);
		
		if(checkPwFlag == 1) {	// 로그인 성공시 세션에 아이디와 로그인 유효값을 설정
			HttpSession session =  request.getSession();
			session.setAttribute("sessionId", mid);
			session.setAttribute("ValidMem", "yes");
			
			MemberDto dto = dao.memberInfoDao(mid);
			String mname = dto.getMname();
			
			model.addAttribute("mname", mname);
			
		}
		
		return "loginOk";
	}
	
	@RequestMapping(value = "writeForm")
	public String writeForm(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		HttpSession session =  request.getSession();
		String sid = (String) session.getAttribute("sessionId");
		
		if(sid.equals(null)) {
			return "redirect:login";
		}
		else {
			MemberDto dto = dao.memberInfoDao(sid);
			String mname = dto.getMname();
			String mid = dto.getMid();
			
			model.addAttribute("mid", mid);
			model.addAttribute("mname", mname);
			
			
			return "writeForm";
		}
	}
	
	@RequestMapping(value = "write")
	public String write(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HttpSession session =  request.getSession();
		String sid = (String) session.getAttribute("sessionId");

		MemberDto dto = dao.memberInfoDao(sid);
		
		String mname = dto.getMname();
		String mid = dto.getMid();
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");
		
		dao.writeDao(mid, mname, ftitle, fcontent);
		
		
		return "redirect:list";
	}
	
}
