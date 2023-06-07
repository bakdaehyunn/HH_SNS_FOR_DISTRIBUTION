package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.CommentInfoVO;

public interface CommentInfoService {
	int create(CommentInfoVO commentvo);
	CommentInfoVO read(int commentId);
	CommentInfoVO read_reply(int replyId);
	List<CommentInfoVO> read_all(int replyId);
	int update(int commentId, String commentContent);
	int delete(int commentId);
	
}
