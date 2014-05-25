/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw06.bl.command;
import by.epam.lw06.bl.LoginLogic;
import by.epam.lw06.bl.DbException;
import by.epam.lw06.bl.manager.ConfigurationManager;
import by.epam.lw06.bl.manager.MessageManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Anna
 */
public class LoginCommand implements ICommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException  {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            if (LoginLogic.checkLogin(login, pass)) { 
                HttpSession session = request.getSession(true);
                session.setAttribute("user", login);
                request.setAttribute("user", login);
                page = ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            }
            else {
                request.setAttribute("errorMessage", MessageManager.getInstance().
                        getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        } catch (DbException| InterruptedException ex) {
            request.setAttribute("errorMessage", MessageManager.getInstance().
                        getProperty(MessageManager.BASE_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } 
        return page;
    }
    
}
