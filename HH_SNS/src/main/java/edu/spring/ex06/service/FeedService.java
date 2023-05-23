package edu.spring.ex06.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;

public interface FeedService {
	int create(FeedVO feedvo);
	List<FeedVO> readAll();
	List<FeedVO> readAllbyId(String userId);
	FeedVO read(int feedId);
	FeedVO read(String userId);
	int update(int feedId, String feedContent);
	int delete(int feedId);
	int getTotalComments();
	int getTotalLikes();
}
