package edu.spring.ex06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.CommentInfoDAO;
import edu.spring.ex06.persistence.FeedDAO;
import edu.spring.ex06.persistence.FollowDAO;
import edu.spring.ex06.persistence.LikeInfoDAO;
import edu.spring.ex06.persistence.NotiDAO;
import edu.spring.ex06.persistence.ReplyDAO;
import edu.spring.ex06.persistence.UserInfoDAO;

@Service
public class UserInfoServiceImple implements UserInfoService {
	private static Logger logger =
			LoggerFactory.getLogger(UserInfoServiceImple.class);
	
	@Autowired
	private UserInfoDAO userinfoDAO;
	
	@Autowired
	private FeedDAO feedDAO;
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private LikeInfoDAO likeDAO;
	
	@Autowired
	private NotiDAO notiDAO;
	
	@Autowired
	private FollowDAO followDAO;
	
	@Autowired
	private CommentInfoDAO commentDAO;
	
	@Override
	public int create(UserInfoVO vo) { // 회원정보 생성(회원가입)
		logger.info("create() 호출 : vo = " + vo.toString());
		return userinfoDAO.insert(vo);
	}

	@Override
	public UserInfoVO read(String userId) { // 회원 정보 불러오기
		logger.info("read() 호출 : userId = " + userId);
		return userinfoDAO.select(userId);
	}

	@Override
	public int read(String userId, String UserPassword) { // 로그인(회원정보 확인)
		logger.info("read() 호출 ");
		logger.info("userId = " + userId);
		logger.info("UserPassword = " + UserPassword);
		return userinfoDAO.select(userId, UserPassword);
	}
	
	@Override
	public int readUserId(String userId) { // 아이디 중복체크
		logger.info("readUserId() 호출 : userId = " + userId);
		return userinfoDAO.selectUserId(userId);
	}

	@Override
	public int readUserEmail(String userEmail) { // 이메일 중복체크
		logger.info("readUserEmail() 호출 : userEmail = " + userEmail);
		return userinfoDAO.selectUserEmail(userEmail);
	}

	@Override
	public int update(UserInfoVO vo) { // 회원정보 수정
		logger.info("read() 호출 : vo = " + vo.toString());
		return userinfoDAO.update(vo);
	}
	
	@Transactional(value= "transactionManager") //트랜잭션 적용
	@Override
	public int updateProfile(UserInfoVO vo) throws Exception{ // 프로필정보 수정
		logger.info("updateProfile() 호출 : vo = " + vo.toString());
		userinfoDAO.updateProfile(vo);
		logger.info("update_feedprofile()");
		String userNicknamne = vo.getUserNickname();
		String userProfile = vo.getUserProfile();
		String userId = vo.getUserId();
		feedDAO.update_profile(userNicknamne, userProfile, userId);//피드의 프로필 정보 수정
		replyDAO.update_profile(userNicknamne, userProfile, userId);//댓글의 프로필 정보 수정
		commentDAO.update_profile(userNicknamne, userProfile, userId);//대댓글의 프로필 정보 수정
		return 1;
	}
	
	@Transactional(value= "transactionManager") // 트랜잭션 적용
	@Override
	public int delete(String userId) throws Exception{ // 회원 탈퇴
		logger.info("userIdDelete() 호출 : userId = " + userId);
		userinfoDAO.delete(userId);//유저정보 삭제
		feedDAO.deleteUserId(userId);//유저의 피드 내역 삭제
		notiDAO.deleteUserId(userId);//유저의 알림 삭제
		followDAO.deleteFollow(userId);//유저의 팔로워,팔로잉 내역 삭제
		replyDAO.deleteUserId(userId);// 유저의 댓글 내역 삭제
		commentDAO.deleteUserId(userId); // 유저의 대댓글 내역 삭제
		likeDAO.deleteUserid(userId); // 유저의 좋아요 내역 삭제
		return 1;
	}

	

}
