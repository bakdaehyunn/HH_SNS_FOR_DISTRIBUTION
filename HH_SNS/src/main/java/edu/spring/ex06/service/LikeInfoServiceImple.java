package edu.spring.ex06.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex06.domain.LikeInfoVO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@Service
public class LikeInfoServiceImple implements LikeInfoService{
	private static final Logger logger =
			LoggerFactory.getLogger(LikeInfoServiceImple.class);
	
	@Autowired
	private UserInfoDAO userDAO;
	
	@Autowired
	private FeedDAO feedDAO;
	
	@Autowired
	private LikeInfoDAO likeDAO;


	@Override
	public int create(LikeInfoVO vo) {
		logger.info("★ LikeServiceImple 등록 : vo = " + vo.toString());
		likeDAO.insert(vo);
		logger.info("좋아요 등록");
		
		feedDAO.updateLikeCnt(1, vo.getFeedId());
		return 1;
	}

	@Override
	public LikeInfoVO read(int likeId) {
		logger.info("★ LikeServiceImple 좋아요 번호 : " + likeId);
		return likeDAO.select(likeId);
	}
	
	@Override
	public LikeInfoVO read_feedId(int feedId) {
		logger.info("★ LikeServiceImple 좋아요 피드 아이디 : " + feedId);
		return likeDAO.select(feedId);
	}
	

	@Override
	public int read_check(String userId) {
		logger.info("★ LikeServiceImple 좋아요 유저 중복체크 : " + userId);
		return likeDAO.select_check(userId);
	}
	
	@Override
	public int read_check_feedId(int feedId) {
		logger.info("★ LikeServiceImple 좋아요 유저 중복체크 : " + feedId);
		return likeDAO.select_check_feedId(feedId);
	}
	
	@Override
	public List<LikeInfoVO> read_all() {
		logger.info("★ LikeServiceImple 전체 검색");
		return likeDAO.select_all();
	}

	@Override
	public List<LikeInfoVO> read_all(int feedId) {
		logger.info("★ LikeServiceImple 좋아요 피드 전체 검색");
		return likeDAO.select_all(feedId);
	}

	@Override
	public int update(int likeId, LikeInfoVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(int likeId) {
		logger.info("★ LikeServiceImple 삭제 : likeId = " + likeId);
		likeDAO.delete(likeId);
		logger.info("좋아요 삭제 완료");
		
		// feedId 가져오기 ~
		
		
//		logger.info("피드 좋아요 개수 삭제할 피드 번호 : " + feedId);
//		feedDAO.updateLikeCnt(-1, feedId);
//		logger.info("피드 업데이트 완료");
		
		
		return 1;
	}









	
	
}
