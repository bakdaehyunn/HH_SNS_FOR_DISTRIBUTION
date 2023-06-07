package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.CommentInfoVO;
public interface CommentInfoDAO {
	int insert(CommentInfoVO vo);
	CommentInfoVO select(int commentId);
	CommentInfoVO select_reply(int replyId);
	List<CommentInfoVO> select_all(int replyId);
	int update(int commentId, String commentContent);
	int delete(int commentId);
	
}
