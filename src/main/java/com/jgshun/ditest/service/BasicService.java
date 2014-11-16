/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgshun.ditest.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgshun
 */
public class BasicService {

    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(BasicService.class.getClassLoader().getResourceAsStream("di.properties"));
        } catch (IOException ex) {
            Logger.getLogger(BasicService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BasicService() {

        Method[] ms = this.getClass().getDeclaredMethods();
        for (Method m : ms) {
            String name = m.getName();
            if (name.startsWith("set")) {
                String na = name.substring(3, 4).toLowerCase()+name.substring(4);
                String nameSpace = prop.getProperty(na);
                if (nameSpace != null && !"".equals(nameSpace)) {
                    try {
                        m.invoke(this, Class.forName(nameSpace).newInstance());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(BasicService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(BasicService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(BasicService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(BasicService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(BasicService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
