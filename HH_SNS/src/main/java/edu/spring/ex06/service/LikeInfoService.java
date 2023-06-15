package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.LikeInfoVO;

public interface LikeInfoService {
	int create(LikeInfoVO vo, String feedUserId) throws Exception;
	LikeInfoVO read(int likeId);
	int read_check(String userId, int feedId);
	List<LikeInfoVO> read_check_all_id(String userId);
	List<LikeInfoVO> read_all(int feedId);
	List<LikeInfoVO> read_all_id(String userId);
	int delete(int likeId) throws Exception;
}
