package edu.spring.ex06.persistence;

import edu.spring.ex06.domain.LikeInfoVO;

public interface LikeInfoDAO {
	int insert(LikeInfoVO vo);
	int update(int likeId, int feedId, int replyId, int commentId);
	int delete(int likeId);
	/*
	 * 좋아요 저장 = 등록
	 * 좋아요 개수 = 수정
	 * 좋아요 조회 = 검색
	 * */
	
}
