package com.heekwoncompany.myfreeboard.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heekwoncompany.myfreeboard.dao.mapper.IDao;
import com.heekwoncompany.myfreeboard.dto.FreeBoardDto;
import com.heekwoncompany.myfreeboard.dto.MemberDto;




@Controller
public class FBoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/")
	public String home() {
	
		return "redirect:list";
	}

	//-----------------------MEMBER 관리-----------------------------// 
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
		
		
		
		model.addAttribute("checkIdFlag", checkIdFlag);
		model.addAttribute("checkPwFlag", checkPwFlag);
		
		if(checkIdFlag == 1) {
			if(checkPwFlag == 1) {	// 로그인 성공시 세션에 아이디와 로그인 유효값을 설정
				HttpSession session =  request.getSession();
				session.setAttribute("sessionId", mid);
				session.setAttribute("ValidMem", "yes");
				
				return "redirect:list";
				
			}else {
				return "loginOk";
				}
		}else {
			return "loginOk";
		}
		
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		session.invalidate();
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "deleteMember")
	public String deleteMember(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String mid = request.getParameter("mid");
		dao.deleteMemberDao(mid);
		
		HttpSession session =  request.getSession();
		session.invalidate();
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "modifyMember")
	public String modifyMember(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String mid = request.getParameter("mid");
		MemberDto dto = dao.memberInfoDao(mid);
		
		model.addAttribute("minfo", dto);
		
		return "modifyMember";
	}
	
	@RequestMapping(value = "modifyMemberOk")
	public String modifyMemberOk(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		dao.modifyMemberDao(mpw, mname, memail, mid);
		
		return "redirect:list";
	}
	
	
	//-----------------------MEMBER 관리-----------------------------//
	
	
	//-----------------------FREEBOARD 관리-----------------------------//
	@RequestMapping(value = "writeForm")
	public String writeForm(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		HttpSession session =  request.getSession();
		String sid = (String) session.getAttribute("sessionId");
		
		if(sid == null) {
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
	
	
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HttpSession session =  request.getSession();
		String sid = (String) session.getAttribute("sessionId");
		
		
		String mname = "";
		
		if(sid == null) {
			mname = "로그인을 해주세요.";
			model.addAttribute("mname", mname);
			
		}
		else {
			MemberDto dto = dao.memberInfoDao(sid);
			mname = dto.getMname();
			String mid = dto.getMid();
			model.addAttribute("mname", mname+" 님 어서오세요.");
			model.addAttribute("mid", mid);
			
		}
		ArrayList<FreeBoardDto> dtos = dao.listDao();
		int count = dtos.size();
		
		model.addAttribute("count", count);
		model.addAttribute("list", dtos);
		
		return "list";
	}
	
	@RequestMapping(value = "contentView")
	public String contentView(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		
		String fnum = request.getParameter("fnum");
		dao.uphitDao(fnum);
		FreeBoardDto dto = dao.contentViewDao(fnum);
		
		model.addAttribute("content", dto);
		
		return "contentView";
	}
	
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String fnum = request.getParameter("fnum");
		dao.deleteDao(fnum);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "modify")
	public String modify(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String fnum = request.getParameter("fnum");
		
		HttpSession session =  request.getSession();
		String sid = (String) session.getAttribute("sessionId");
		model.addAttribute("sid", sid);
		
		String mname="";
		
		if(sid == null) {
			mname = "Guest";
			model.addAttribute("mname", mname);
			
			return "modify";
			
		}else {
			MemberDto mdto = dao.memberInfoDao(sid);
			FreeBoardDto fdto = dao.contentViewDao(fnum);
			model.addAttribute("content", fdto);
			
			String mid = mdto.getMid();
			String fid = fdto.getFid();
			mname = mdto.getMname();
			model.addAttribute("mname", mname);
			model.addAttribute("mid", mid);
			model.addAttribute("fid", fid);
			
			return "modify";
		}
		
		
	}

	@RequestMapping(value = "modifyOk")
	public String modifyOk(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String fnum = request.getParameter("fnum");
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");
		
		dao.modifyDao(ftitle, fcontent, fnum);
		
		return "redirect:list";
	}
	
	
	//-----------------------FREEBOARD 관리-----------------------------//
	
}
