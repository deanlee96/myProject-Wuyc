//package aw.select;
//
//import java.util.List;
//
//import javax.ws.rs.core.Application;
//
//import org.hibernate.sql.Select;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import aw.select.domain.RequestData;
//import aw.select.domain.ResponseData;
//import aw.select.domain.TestData;
//import aw.select.interfaces.SelectPrice;
//import aw.select.repository.RequestRepository;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@SpringBootTest(classes = {Application.class})
////@Rollback(value = true)
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
//
//public class SqlserverTest {
//	@Autowired
//	RequestRepository requestRepository;
//	
//	@Test
//	public void select(){
//		//ResponseData recive = requestRepository.findByBarCode2("10001989");
//		//System.out.println(recive.toString());
//		
//	}
//	@Test
//	public void getId(){
//	//	String testId=requestRepository.getId("10000001");
//		//System.out.println(testId);
//		
//	}
//	
//	@Test
//	public void getTest(){
//	//	TestData testData = requestRepository.test("10000001");
//		//System.out.println(testData.toString());
//	}
//
//}
