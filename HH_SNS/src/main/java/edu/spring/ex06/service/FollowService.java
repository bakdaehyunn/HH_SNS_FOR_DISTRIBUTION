package edu.spring.ex06.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

public interface FollowService {
	int create(String followerUserId,String followingUserId)throws Exception; // 팔로우 등록
	int readFollowerCnt(String followerUserId); // 팔로워 수 불러오기
	int readFollowingCnt(String followingUserId); // 팔로잉 수 불러오기
	int delete(String followerUserId, String followingUserId)throws Exception; // 팔로우 취소 
	List<UserInfoVO> readFollowingList(String followingUserId); // 팔로워 리스트 불러오기
	List<UserInfoVO> readFollowerList(String followerUserId); // 팔로잉 리스트 불러오기
	int readFollowingCheck(String followerUserId, String followingUserId); // 팔로우 확인 
	List<UserInfoVO> readTagList(String followerUserId, String followingUserId);// 태그할 친구 리스트 불러오기
}
