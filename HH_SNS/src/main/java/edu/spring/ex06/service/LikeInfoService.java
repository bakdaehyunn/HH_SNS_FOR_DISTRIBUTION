package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.LikeInfoVO;

public interface LikeInfoService {
	int create(LikeInfoVO vo) throws Exception;
	LikeInfoVO read(int likeId);
	LikeInfoVO read_feedId(int feedId);
	int read_check(String userId);
	int read_check_feedId(int feedId);
	List<LikeInfoVO> read_all(int feedId);
	List<LikeInfoVO> read_all_Id(String userId);
	int update(int likeId, LikeInfoVO vo);
	int delete(int likeId) throws Exception;
}
