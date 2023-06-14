package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.ReplyVO;



public interface ReplyDAO {
	int insert(ReplyVO vo);
	List<ReplyVO> select(int feedId);
	int update(int replyId, String replyContent);
	int delete(int replyId);
	int updateCommentCnt(int amount, int replyId);
	
}
