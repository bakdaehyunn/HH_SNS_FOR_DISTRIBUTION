package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.CommentInfoVO;

public interface CommentInfoService {
	int create (CommentInfoVO commentvo)throws Exception;
	CommentInfoVO read(int commentId);
	CommentInfoVO read_reply(int replyId);
	List<CommentInfoVO> read_all(int replyId);
	List<CommentInfoVO> read_all_commentid(int commentId);
	int update(int commentId, String commentContent);
	int delete(int commentId)throws Exception;
	
}
