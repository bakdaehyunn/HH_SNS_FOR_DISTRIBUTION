package edu.spring.ex06.persistence;

import edu.spring.ex06.domain.CommentInfoVO;
public interface CommentInfoDAO {
	int insert(CommentInfoVO vo);
	CommentInfoVO select(int commentId);
	int update(int commentId, String commentContent);
	int delete(int commentId);
	
}
