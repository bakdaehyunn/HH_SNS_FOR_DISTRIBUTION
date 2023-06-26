package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

public interface FeedDAO {
	int insert(FeedVO vo); // 피드 작성(회원)
	List<FeedVO> selectAll(); // 전체 피드 츌력 = 전체 검색 (최신순)(비회원)
	List<FeedVO> selectAllbyId(String userId); // 개인 피드 출력 = 아이디 기준(회원)
	FeedVO select(int feedId); // 피드 상세 출력 = 피드 번호 기준 검색(비회원)
	FeedVO select(String userId);
	int update_content(int feedId, String feedContent); // 피드 수정(회원)
	int delete(int feedId); // 피드 번호 기준 피드 삭제(회원)
	int updateLikeCnt(int amount, int feedId); // 개인 좋아요 수 출력
	int updateReplyCnt(int amount, int feedId); // 개인 댓글 수 출력
	int update_profile(String userNickname, String userProfile, String userId);
	// 피드 상세 != 개인피드
	// detail = userdetail
	// 게시글 클릭 = 유저 아이디로 게시글 출력
	int deleteUserId(String userId); 
	
}
