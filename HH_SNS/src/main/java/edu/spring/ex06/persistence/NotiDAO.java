package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.domain.UserInfoVO;


public interface NotiDAO {
	int insert(String senderId, String receiverId, String notiCategory);
	List<NotiVO> selectList(String receiverId);
	int selectCheck(String receiverId);
	int delete(int notiId);
	int update(int notiId);

}
