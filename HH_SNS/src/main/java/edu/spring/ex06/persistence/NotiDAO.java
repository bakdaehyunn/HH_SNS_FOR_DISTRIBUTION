package edu.spring.ex06.persistence;

import java.util.List;

import edu.spring.ex06.domain.FeedVO;
import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.domain.UserInfoVO;


public interface NotiDAO {
	int insert(NotiVO vo); // 알림 등록
	List<NotiVO> selectList(String receiverId);// 알림 리스트 불러오기
	int selectCheck(String receiverId); // 알림 확인
	int update(String receiverId); // 알림 읽음 변경
	int deleteNotiId(int notiId); // 알림 삭제
	int delete(String senderId, String receiverId);//팔로우 정보로 알림 삭제
	int deleteUserId(String receiverId); // 회원정보로 알림 내역 삭제

}
