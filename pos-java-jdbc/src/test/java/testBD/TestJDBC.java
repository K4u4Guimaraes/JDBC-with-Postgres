package testBD;

import java.util.List;

import org.junit.Test;

import conn_jdbc.SingleConnection;
import dao.UserPosDAO;
import junit.framework.TestCase;
import model.BeanUserPhone;
import model.Phone;
import model.UserPosJava;

public class TestJDBC{
	
	@Test
	public void initDataBase() {
		UserPosDAO dao = new UserPosDAO();
		UserPosJava jav = new UserPosJava();
		jav.setName("Dora Dora");
		jav.setEmail("CrazyDiamon@gmail.com");
		
		jav.setName("DoraDora");
		jav.setEmail("CrazyDiamon2@gmail.com");
		
		dao.insertUserDAO(jav);
		
	}
	
	@Test
	public void initList() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserPosJava> li = dao.lis();
			
			for(UserPosJava listUser : li) {
				System.out.println("name: "+listUser.getName());
				System.out.println("email: "+listUser.getEmail());
				System.out.println("   ");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void initUpdate() {
		UserPosDAO dao = new UserPosDAO();
		try {
			UserPosJava objDAO = dao.search(15L);
			objDAO.setName("name updated");
			dao.Update(objDAO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initDelete(){
		UserPosDAO dao = new UserPosDAO();
		try {
			UserPosJava user = dao.search(16L);
			dao.Delete(15L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initPhoneInsert() {
		Phone phone = new Phone();
		phone.setNumber("(11) 955562 - 23311");
		phone.setIdUser(15);
		phone.setType("SmartPhone");
		UserPosDAO dao =  new UserPosDAO();
		dao.insertPhone(phone);
		
		
	}
	
	@Test
	public void initInnerJoin(){
		UserPosDAO dao = new UserPosDAO();
		
		List<BeanUserPhone> lists = dao.listUserPhone(12);
		for(BeanUserPhone beanList: lists) {
			System.out.println("Name: " +beanList.getName());
			System.out.println("Email: " +beanList.getEmail());
			System.out.println("Number:" +beanList.getNumber());
		}
		
	}
	
	@Test
	public void initDeleteUserPhone() {
		UserPosDAO dao = new UserPosDAO();
		dao.deleteUserPhone(15);
		
	}
}
