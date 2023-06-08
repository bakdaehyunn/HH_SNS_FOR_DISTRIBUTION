package edu.spring.ex06.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.LikeInfoVO;
import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.NotiDAO;
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
	
	@Autowired
	private NotiDAO notiDAO; 

	
	@Transactional(value= "transactionManager")
	@Override
	public int create(LikeInfoVO vo, String feedUserId) throws Exception{
		logger.info("★ LikeServiceImple 등록 : vo = " + vo.toString());
		likeDAO.insert(vo);
		logger.info("좋아요 등록");
		if(!(vo.getUserId().equals(feedUserId))) {
		notiDAO.insert(vo.getUserId(), feedUserId, "like");
		}
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
	public int read_check(String userId, int feedId) {
		logger.info("★ LikeServiceImple 좋아요 중복체크 : " + userId + ", " + feedId);
		return likeDAO.select_check(userId, feedId);
	}
	
	@Override
	public List<LikeInfoVO> read_check_all_id(String userId) {
		logger.info("★ LikeServiceImple 좋아요 아이디 확인 : " + userId);
		return likeDAO.select_check_all_Id(userId);
	}

	@Override
	public List<LikeInfoVO> read_all(int feedId) {
		logger.info("★ LikeServiceImple 좋아요 피드 전체 검색");
		return likeDAO.select_all(feedId);
	}

	@Override
	public List<LikeInfoVO> read_all_id(String userId) {
		logger.info("★ LikeServiceImple 좋아요 아이디 전체 검색");
		return likeDAO.select_all_Id(userId);
	}

	@Transactional(value= "transactionManager")
	@Override
	public int delete(int likeId) throws Exception{
		
		// likeId에 해당하는 좋아요 정보 가져오기
	    LikeInfoVO likeInfo = likeDAO.select(likeId);
	    int feedId = likeInfo.getFeedId(); // 가져온 좋아요 정보에서 feedId 얻기
	    
	    likeDAO.delete(likeId); // 좋아요 삭제
	    
	    logger.info("좋아요 삭제 완료 : " + likeId);
	    
	    logger.info("피드 좋아요 개수 삭제할 피드 번호 : " + feedId);
	    feedDAO.updateLikeCnt(-1, feedId); // 피드의 좋아요 개수 업데이트
	    
	    logger.info("피드 업데이트 완료");
		
		
		return 1;
	}



}
