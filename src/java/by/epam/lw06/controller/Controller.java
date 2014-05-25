/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw06.controller;
import by.epam.lw06.bl.RequestHelper;
import by.epam.lw06.bl.command.ICommand;
import by.epam.lw06.bl.manager.ConfigurationManager;
import by.epam.lw06.bl.manager.MessageManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anna
 */
public class Controller extends HttpServlet {
    RequestHelper requestHelper = RequestHelper.getInstance();
    
    public Controller() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            processRequest(request, response);
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String page;
        String error = "errorMessage";
        try {
            ICommand command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            request.setAttribute(error, MessageManager.getInstance().
                    getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            request.setAttribute(error, MessageManager.getInstance().
                    getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
    
}
