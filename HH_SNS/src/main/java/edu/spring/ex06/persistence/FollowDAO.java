package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;


public interface FollowDAO {
	int insert(String followerUserId, String followingUserId); // 팔로우 등록
	int selectFollowerCnt(String followerUserId); // 팔로워 수 불러오기
	int selectFollowingCnt(String followingUserid); // 팔로잉 수 불러오기
	List<UserInfoVO> selectFollowingList(String followingUserId); // 팔로워 리스트 불러오기
	List<UserInfoVO> selectFollowerList(String followerUserId);// 팔로잉 리스트 불러오기
	int selectFollowingCheck(String followerUserId, String followingUserId); // 팔로우 확인 
	List<UserInfoVO> selectTagList(String followerUserId, String followingUserId); // 태그할 친구 리스트 불러오기
	int delete(String followerUserId, String followingUserId); // 팔로우 취소
	int deleteFollow(String userId); //유저아이디로 팔로우 내역 삭제
	
	
}
