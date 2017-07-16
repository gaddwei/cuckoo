package test;


import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cuckoo.account.dao.UserDAO;
import cn.cuckoo.account.entity.User;


public class util {
	 @Test
	 public void test(){
		 String str = "1";
		 System.out.println("[0-9]".matches(str));
	 }
	 
}
