/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgshun.ditest.dao;

import org.junit.Test;
import com.jgshun.ditest.domain.User;
import org.junit.Before;

/**
 *
 * @author jgshun
 */
public class UserJDBCDAOTest {
    UserJDBCDAO ujdbcdao = null;
    @Before
    public void newObject(){
         ujdbcdao = new UserJDBCDAO();
    }
    
    
    @Test
    public void testUpdate(){
        User user = ujdbcdao.show(2);
        user.setNickname("张三");
        user.setPassword("1234567890");
        user.setType(1);
        
        ujdbcdao.update(user);
    }
    
    @Test
    public void testAdd(){
        User user = new User();
        user.setUsername("lisi");
        user.setNickname("李四");
        user.setPassword("123123");
        user.setType(1);
        
        ujdbcdao.add(user);
    }
    
    
    @Test
    public void testShow(){
        System.out.println( ujdbcdao.show(1));
    }
    
    
   @Test
   public void test1(){
       for(User user :ujdbcdao.list()){
           System.out.println(user.toString());
       }
   }
   
   
   
   
   
   
   
   
   
}
