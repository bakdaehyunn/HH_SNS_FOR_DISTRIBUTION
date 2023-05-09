package edu.spring.ex06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.persistence.FeedDAO;

@Service
public class FeedServiceImple implements FeedService{
	private static final Logger logger =
			LoggerFactory.getLogger(FeedServiceImple.class);
	
	@Autowired
	private FeedDAO dao;

	@Override
	public int create(FeedVO vo) {
		logger.info("★ FeedService 등록 : " + vo.toString());
		return dao.insert(vo);
	}// end create()

	@Override
	public FeedVO read(int feedId) {
		logger.info("★ FeedService 검색 : " + feedId);
		return dao.select(feedId);
	}

	@Override
	public int update(int feedId, FeedVO vo) {
		logger.info("★ FeedService 수정 : " + vo.toString());
		return dao.update(feedId, vo);
	}

	@Override
	public int delete(int feedId) {
		logger.info("★ FeedService 삭제 : " + feedId);
		return dao.delete(feedId);
	}

	@Override
	public int getTotalLikes() {
		logger.info("★ FeedService 좋아요 수");
		return dao.getTotalLike();
	}
	
	@Override
	public int getTotalComments() {
		// TODO Auto-generated method stub
		return 0;
	}


}
