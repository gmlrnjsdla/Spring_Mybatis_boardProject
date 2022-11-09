package com.heekwoncompany.myfreeboard.dao.mapper;

public interface IDao {

	//member 관련 메소드
	public void joinMemberDao(String mid, String mpw, String mname, String memail); //회원가입
	public int checkIdDao(String mid); // 회원가입 여부 확인
}
