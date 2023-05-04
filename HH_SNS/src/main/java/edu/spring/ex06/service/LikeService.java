package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.LikeInfoVO;

public interface LikeService {
	int create(LikeInfoVO vo) throws Exception;
	List<LikeInfoVO> read(int feedId);
	int update(int likeId, LikeInfoVO vo);
	int delete(int likeId, int feedId) throws Exception;
}
