package edu.spring.ex06.service;

import edu.spring.ex06.domain.FeedVO;

public interface FeedService {
	int create(FeedVO vo);
	FeedVO read(int boardId);
	int update(FeedVO vo);
	int delete(int boardId);
	int getTotalComments();
	int getTotalLikes();
}
