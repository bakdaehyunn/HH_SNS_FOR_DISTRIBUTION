package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.ReplyVO;



public interface ReplyService {
	int create(ReplyVO vo) throws Exception;
	List<ReplyVO> read(int feedId);
	int update(int replyId, String replyContent);
	int delete(int replyId, int feedId) throws Exception;
}
