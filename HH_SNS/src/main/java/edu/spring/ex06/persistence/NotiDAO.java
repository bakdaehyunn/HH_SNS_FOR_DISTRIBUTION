package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.domain.UserInfoVO;


public interface NotiDAO {
	int insert(NotiVO vo);
	List<NotiVO> selectList(String receiverId);
	int selectCheck(String receiverId);
	int delete(String senderId, String receiverId);
	int update(String receiverId);
	int deleteNotiId(int notiId);
	int deleteUserId(String receiverId);

}
