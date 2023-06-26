package edu.spring.ex06.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FollowDAO;
import edu.spring.ex06.persistence.NotiDAO;


@Service
public class FollowServiceImple implements FollowService {
	private static Logger logger = 
			LoggerFactory.getLogger(FollowServiceImple.class);

	@Autowired
	private FollowDAO followDAO;
	
	@Autowired
	private NotiDAO notiDAO;
	
	@Transactional(value= "transactionManager") // 트랜잭션 적용
	@Override
	public int create(String followerUserId, String followingUserId) throws Exception{ // 팔로우 등록
		logger.info("create() 호출 ");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId" + followingUserId);
		followDAO.insert(followerUserId, followingUserId);// 팔로우 등록
		NotiVO vo = new NotiVO(0, followerUserId, followingUserId, "follow", 0, 0);
		notiDAO.insert(vo); //알림 등록
		return 1;
	}
	@Override
	public List<UserInfoVO> readFollowerList(String followerUserId) { // 팔로워 리스트 불러오기
		logger.info("readFollowerList() 호출");
		logger.info("followerUserId : " + followerUserId);
		return followDAO.selectFollowerList(followerUserId);
	}

	@Override
	public List<UserInfoVO> readFollowingList(String followingUserId) { // 팔로잉 리스트 불러오기
		logger.info("readFollowingList() 호출");
		logger.info("followingUserId : " + followingUserId);
		return followDAO.selectFollowingList(followingUserId);
	}

	@Override
	public int readFollowingCheck(String followerUserId, String followingUserId) { // 팔로우 확인 
		logger.info("readFollowingCheck() 호출");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId : " + followingUserId);
		return followDAO.selectFollowingCheck(followerUserId, followingUserId);
	}

	@Override
	public List<UserInfoVO> readTagList(String followerUserId, String followingUserId) { // 태그할 친구 리스트 불러오기
		logger.info("readTagList() 호출");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followingUserId : " + followingUserId);
		return followDAO.selectTagList(followerUserId, followingUserId);
	}

	@Override
	public int readFollowerCnt(String followerUserId) { // 팔로워 수 불러오기
		logger.info("readFollower() 호출 : followerUserId = " + followerUserId);
		return followDAO.selectFollowerCnt(followerUserId);
	}
	
	@Override
	public int readFollowingCnt(String followingUserId) { // 팔로잉 수 불러오기
		logger.info("readFollowing() 호출 : followingUserId = " + followingUserId);
		return followDAO.selectFollowingCnt(followingUserId);
	}
	
	@Transactional(value= "transactionManager") // 트랜잭션 적용
	@Override
	public int delete(String followerUserId, String followingUserId) throws Exception{ // 팔로우 취소 
		logger.info("delete() 호출 ");
		logger.info("followerUserId : " + followerUserId);
		logger.info("followerUserId : " + followingUserId );
		followDAO.delete(followerUserId, followingUserId); //팔로우 취소
		notiDAO.delete(followerUserId, followingUserId); //알림 삭제
		return 1;
	}
}
