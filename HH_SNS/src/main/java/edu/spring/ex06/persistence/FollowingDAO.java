package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.FollowingVO;

public interface FollowingDAO {
	int insert(FollowingVO vo); // 피드 작성(회원)
	
}
