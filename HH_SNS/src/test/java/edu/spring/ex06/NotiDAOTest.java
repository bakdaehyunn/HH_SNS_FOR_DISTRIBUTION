package edu.spring.ex06;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex06.domain.NotiVO;
import edu.spring.ex06.domain.UserInfoVO;
import edu.spring.ex06.persistence.FollowDAO;
import edu.spring.ex06.persistence.NotiDAO;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class NotiDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(NotiDAOTest.class);

	

	
	@Autowired
	private NotiDAO notiDao;


	@Test
	public void testDAO() {
		//notiTestInsert();
		notiTestSelect();
		//notiTestUpdate();
		notiTestDelete();
		
	}// end testDAO()

	private void notiTestDelete() {
		int notiId = 1;
		int result = notiDao.delete(notiId);
		if(result == 1) {
			logger.info("delete 성공");
		}
		
	}

	private void notiTestUpdate() {
		int notiId = 1;
		int result = notiDao.update(notiId);
		if(result == 1) {
			logger.info("update 성공");
		}
		
	}

	private void notiTestSelect() {
		String receiverId="b";
		List<NotiVO> list = notiDao.selectList(receiverId);
		for(NotiVO vo : list) {
			logger.info(vo.toString());
		}
		
	}
	

	private void notiTestInsert() {
		
		NotiVO vo = new NotiVO(0, "a", "b", "msg", 0, 0);
		int result = notiDao.insert("a","b","msg");
		if(result == 1) {
			logger.info("insert 성공");
		}
	}

	
	
	


}
