package edu.spring.ex06.persistence;

import java.util.Date;
import java.util.List;

import edu.spring.ex06.domain.FeedVO;

public interface FeedDAO {
	int insert(FeedVO vo); // 피드 작성(회원)
	List<FeedVO> selectAll(Date feedDate); // 전체 피드 츌력 = 전체 검색 (최신순)(비회원)
	FeedVO select (String userId, int feedId); // 피드 상세 출력 = 피드 번호 기준 검색(비회원)
	int update(String userId, int feedId, FeedVO vo); // 피드 수정(회원)
	int delete(String usserId, int feedId); // 피드 번호 기준 피드 삭제(회원)
	List<FeedVO> selectAllbyId (String userId); // 개인 피드 출력 = 아이디 기준(회원)
	
	// 피드 상세 != 개인피드
	// detail = userdetail
	// 게시글 클릭 = 유저 아이디로 게시글 출력
	
}
