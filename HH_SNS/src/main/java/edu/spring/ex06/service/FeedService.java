package edu.spring.ex06.service;

import javax.servlet.http.HttpSession;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

public interface FeedService {
	int create(FeedVO feedvo, UserInfoVO userinfovo, HttpSession session);
	FeedVO read(int feedId);
	int update(int feedId, FeedVO vo);
	int delete(int feedId);
	int getTotalComments();
	int getTotalLikes();
}
