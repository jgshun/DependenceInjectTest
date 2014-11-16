/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgshun.ditest.service;

import com.jgshun.ditest.dao.UserJDBCDAO;
import com.jgshun.ditest.domain.User;

/**
 *
 * @author jgshun
 */
public class UserService extends BasicService{
    private UserJDBCDAO ujdbcdao ;

    public UserJDBCDAO getUjdbcdao() {
        return ujdbcdao;
    }

    public void setUjdbcdao(UserJDBCDAO ujdbcdao) {
        this.ujdbcdao = ujdbcdao;
    }
    
    
    public void  update(User user){        
        ujdbcdao.update(user);
    }
}
