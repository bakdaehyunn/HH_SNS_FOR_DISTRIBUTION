package edu.spring.ex06.persistence;

import java.util.Map;

import edu.spring.ex06.domain.UserInfoVO;

public interface UserInfoDAO {
	int insert(UserInfoVO vo); // 회원정보 생성(회원가입)
	UserInfoVO select(String userId); // 회원 정보 불러오기
	int select(String userId,String userPassword);// 로그인(회원정보 확인)
	int selectUserId(String userId); // 아이디 중복체크
	int selectUserEmail(String userEmail);// 이메일 중복체크
	int update(UserInfoVO vo);// 회원정보 수정
	int updateProfile(UserInfoVO vo); // 프로필정보 수정
	int delete(String userId); // 회원 탈퇴
	
}
