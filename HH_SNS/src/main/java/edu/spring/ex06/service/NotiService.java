package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.NotiVO;

public interface NotiService {
	
	List<NotiVO> readList(String receiverId); // 알림 리스트 불러오기
	int readCheck(String receiverId); // 알림 확인
	int update(String receiverId); // 알림 읽음 변경
	int delete(int notiId); // 알림 삭제
} 
