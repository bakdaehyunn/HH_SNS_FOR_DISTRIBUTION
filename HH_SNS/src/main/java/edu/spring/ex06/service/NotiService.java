package edu.spring.ex06.service;

import java.util.List;

import edu.spring.ex06.domain.NotiVO;

public interface NotiService {
	int create(String senderId, String receiverId, String category);
	List<NotiVO> readList(String receiverId);
	int readCheck(String receiverId);
	int update(int notiId);
//	int delete(int notiId);
}
