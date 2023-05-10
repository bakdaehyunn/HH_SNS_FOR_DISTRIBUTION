package edu.spring.ex06.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

public interface FeedService {
	int create(FeedVO feedvo, UserInfoVO userinfovo, HttpSession session);
	List<FeedVO> readAll(int feedId);
	FeedVO read(int feedId);
	int update(int feedId, String feedContent);
	int delete(int feedId);
	int getTotalComments();
	int getTotalLikes();
}
