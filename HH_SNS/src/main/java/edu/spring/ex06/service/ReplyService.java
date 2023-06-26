package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.ReplyVO;



public interface ReplyService {
	int create(ReplyVO vo, String feedUserId) throws Exception; // 댓글 생성
	List<ReplyVO> read(int feedId); // 댓글 리스트 불러오기
	int update(int replyId, String replyContent); // 댓글 수정
	int delete(int replyId, int feedId) throws Exception; // 댓글아이디로 댓글 삭제
}
