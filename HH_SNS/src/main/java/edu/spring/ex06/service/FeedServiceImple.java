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
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}// end create()

	@Override
	public FeedVO read(int boardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(FeedVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int boardId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalComments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalLikes() {
		// TODO Auto-generated method stub
		return 0;
	}

}
