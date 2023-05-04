package edu.spring.ex06.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.LikeInfoVO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@Service
public class LikeServiceImple implements LikeService{
	private static final Logger logger =
			LoggerFactory.getLogger(LikeServiceImple.class);
	
	@Autowired
	private UserInfoDAO userDAO;
	
	@Autowired
	private FeedDAO feedDAO;
	
	@Autowired
	private LikeInfoDAO likeDAO;

	@Transactional(value= "transactionManager")
	@Override
	public int create(LikeInfoVO vo) throws Exception {
		logger.info("★ LikeServiceImple 등록 : vo = " + vo.toString());
		likeDAO.insert(vo);
		logger.info("좋아요 등록");
		
		feedDAO.updateLikeCnt(1, vo.getFeedId());
		return 1;
	}

	@Override
	public List<LikeInfoVO> read(int feedId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(int likeId, LikeInfoVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional(value= "transactionManager")
	@Override
	public int delete(int likeId, int feedId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
