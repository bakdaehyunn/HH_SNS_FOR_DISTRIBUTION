package edu.spring.ex06.service;

import edu.spring.ex06.domain.UserInfoVO;

public interface UserInfoService {
	int create(UserInfoVO vo); // 회원정보 생성(회원가입)
	UserInfoVO read(String userId); // 회원 정보 불러오기
	int read(String userId, String password); // 로그인(회원정보 확인)
	int readUserId(String userId); // 아이디 중복체크
	int readUserEmail(String userEmail); // 이메일 중복체크
	int update(UserInfoVO vo); // 회원정보 수정
	int updateProfile(UserInfoVO vo)throws Exception; // 프로필정보 수정
	int delete(String userId)throws Exception; // 회원 탈퇴
	
	
}
