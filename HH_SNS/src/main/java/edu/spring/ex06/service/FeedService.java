package edu.spring.ex06.service;

import edu.spring.ex06.domain.FeedVO;

public interface FeedService {
	int create(FeedVO vo);
	FeedVO read(int feedId);
	int update(int feedId, FeedVO vo);
	int delete(int feedId);
	int getTotalComments();
	int getTotalLikes();
}
