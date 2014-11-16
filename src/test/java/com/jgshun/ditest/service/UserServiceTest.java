/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgshun.ditest.service;

import org.junit.Before;
import org.junit.Test;
import com.jgshun.ditest.domain.User;


/**
 *
 * @author jgshun
 */
public class UserServiceTest {
    private static UserService us;
    
    @Before
    public void initUs(){
        us = new UserService();
    }
    
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1);
        user.setNickname("nickname1");
        user.setUsername("username1");
        user.setPassword("password1");
        user.setType(3);
        
        us.update(user);
    }
}


