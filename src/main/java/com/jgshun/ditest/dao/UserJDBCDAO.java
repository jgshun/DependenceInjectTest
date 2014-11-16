/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgshun.ditest.dao;

import java.util.ArrayList;
import java.util.List;
import com.jgshun.ditest.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jgshun.ditest.util.ConnectionFactory;
import java.sql.PreparedStatement;

/**
 *
 * @author jgshun
 */
public class UserJDBCDAO {

    private static final String SQL_LIST = "select * from t_user";
    private static final String SQL_SHOW = "select * from t_user where id = ?";
    private static final String SQL_ADD = "insert into t_user(username,nickname,password,type) values(?,?,?,?)";
    private static final String SQL_UPDATE = "update t_user set username = ?,nickname = ?,password = ?,type = ? where id = ?";
    
    
    public void update(User user){
        Connection conn = ConnectionFactory.getConn();
        PreparedStatement sta = null;
        try {
            conn.setAutoCommit(false);
            sta = conn.prepareStatement(SQL_UPDATE);
            sta.setString(1, user.getUsername());
            sta.setString(2, user.getNickname());
            sta.setString(3, user.getPassword());
            sta.setInt(4, user.getType());
            sta.setInt(5, user.getId());
            sta.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserJDBCDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          ConnectionFactory.closeState(sta);
          ConnectionFactory.closeConn(conn);
        }
    }
    
    public void add(User user){
        Connection conn = ConnectionFactory.getConn();
        PreparedStatement sta = null;
        try {
            conn.setAutoCommit(false);
            sta = conn.prepareStatement(SQL_ADD);
            sta.setString(1, user.getUsername());
            sta.setString(2, user.getNickname());
            sta.setString(3, user.getPassword());
            sta.setInt(4, user.getType());
            sta.execute();
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserJDBCDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          ConnectionFactory.closeState(sta);
          ConnectionFactory.closeConn(conn);
        }
    }
    
    public User show(int id){
        User user = new User();
        Connection conn = ConnectionFactory.getConn();
        PreparedStatement sta = null;
        ResultSet rs = null;
        try {
            sta = conn.prepareStatement(SQL_SHOW);
            sta.setInt(1, id);
            rs = sta.executeQuery();
            
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setNickname(rs.getString("nickname"));
                user.setType(rs.getInt("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          ConnectionFactory.closeRs(rs);
          ConnectionFactory.closeState(sta);
          ConnectionFactory.closeConn(conn);
        }
        return user;
    }

    public List<User> list() {
        List<User> list = new ArrayList<User>();
        Connection conn = ConnectionFactory.getConn();
        Statement sta = null;
        ResultSet rs = null;
        try {
            sta = conn.createStatement();
            sta.execute(SQL_LIST);
            rs = sta.getResultSet();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setNickname(rs.getString("nickname"));
                user.setType(rs.getInt("type"));
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserJDBCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          ConnectionFactory.closeRs(rs);
          ConnectionFactory.closeState(sta);
          ConnectionFactory.closeConn(conn);
        }
        return list;
    }

}
