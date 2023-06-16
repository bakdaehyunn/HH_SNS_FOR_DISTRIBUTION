package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.LikeInfoVO;

public interface LikeInfoDAO {
	int insert(LikeInfoVO vo);
	LikeInfoVO select(int likeId);
	int select_check(String userId, int feedId);
	List<LikeInfoVO> select_check_all_Id(String userId);
	List<LikeInfoVO> select_all(int feedId);
	List<LikeInfoVO> select_all_Id(String userId);
	int delete(int likeId);
	/*
	 * 좋아요 저장 = 등록
	 * 좋아요 개수 = 수정
	 * 좋아요 조회 = 검색
	 */
	
}
