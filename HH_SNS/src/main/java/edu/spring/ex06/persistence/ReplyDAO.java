package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.ReplyVO;



public interface ReplyDAO {
	int insert(ReplyVO vo); // 댓글 생성
	List<ReplyVO> select(int feedId); // 댓글 리스트 불러오기
	ReplyVO selectFeedId(int replyId); //댓글 정보 불러오기
	int update(int replyId, String replyContent); // 댓글 수정
	int updateCommentCnt(int amount, int replyId);// 대댓글 카운트 변경
	int updateProfile(String userNickname, String userProfile, String userId); //프로필 정보 변경
	int delete(int replyId);  // 댓글아이디로 댓글 삭제
	int deleteUserId(String userId); // 유저아이디로 댓글 삭제
	
}
