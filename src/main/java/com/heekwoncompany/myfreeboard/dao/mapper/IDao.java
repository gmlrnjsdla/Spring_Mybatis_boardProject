package com.heekwoncompany.myfreeboard.dao.mapper;

import java.util.ArrayList;

import com.heekwoncompany.myfreeboard.dto.FreeBoardDto;
import com.heekwoncompany.myfreeboard.dto.MemberDto;

public interface IDao {

	//----------------------------member 관련 메소드----------------------------//
	public void joinMemberDao(String mid, String mpw, String mname, String memail); //회원가입
	public int checkIdDao(String mid); // 회원가입 여부 확인
	public int checkPwDao(String mid, String mpw); // 아이디 ,비밀번호 일치여부
	public MemberDto memberInfoDao(String mid);
	public void deleteMemberDao(String mid);
	public void modifyMemberDao(String mpw, String mname, String memail, String mid);
	
	//----------------------------board 관련 메소드----------------------------//
	public void writeDao(String mid, String mname, String ftitle, String fcontent);
	public ArrayList<FreeBoardDto> listDao();
	public FreeBoardDto contentViewDao(String fnum);
	public void deleteDao(String fnum);
	public void modifyDao(String ftitle, String fcontent, String fnum);
	public void uphitDao(String fnum);
	
}
