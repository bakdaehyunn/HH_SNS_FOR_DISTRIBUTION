package edu.spring.ex06.service;

import edu.spring.ex06.domain.CommentInfoVO;

public interface CommentInfoService {
	int create(CommentInfoVO commentvo);
	CommentInfoVO read(int commentId);
	int update(int commentId, String commentContent);
	int delete(int commentId);
	
}
