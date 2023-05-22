package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.LikeInfoVO;

public interface LikeInfoDAO {
	int insert(LikeInfoVO vo);
	LikeInfoVO select(int likeId);
	LikeInfoVO select_feedId(int feedId);
	int select_check(String userId);
	int select_check_feedId(int feedId);
	List<LikeInfoVO> select_all();
	List<LikeInfoVO> select_all(int feedId);
	int update(int likeId, int feedId);
	int delete(int likeId);
	/*
	 * 좋아요 저장 = 등록
	 * 좋아요 개수 = 수정
	 * 좋아요 조회 = 검색
	 */
	
}
