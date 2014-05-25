/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw06.bl.manager;
import java.util.ResourceBundle;
/**
 *
 * @author Anna
 */
public class MessageManager {
    private static MessageManager instance;
    private ResourceBundle resourceBundle;
    public static final String BUNDLE_NAME = "by.epam.lw06.properties.messages";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE =
        "SERVLET_EXCEPTION_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE"; 
    public static final String BASE_EXCEPTION_ERROR_MESSAGE = "BASE_EXCEPTION_ERROR_MESSAGE"; 

    private MessageManager() {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);    
    }
    
    public static MessageManager getInstance() {
        if (null == instance) {
            instance = new MessageManager();
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
