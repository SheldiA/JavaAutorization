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
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    public static final String BUNDLE_NAME = "by.epam.lw06.properties.config";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String MAIN_PAGE_PATH =  "MAIN_PAGE_PATH"; 
    
    private ConfigurationManager() {
       resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }
    
    public static ConfigurationManager getInstance() {
        if (null == instance) {
            instance = new ConfigurationManager();
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
