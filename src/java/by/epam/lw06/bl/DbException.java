/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw06.bl;

/**
 *
 * @author Anna
 */
public class DbException extends Exception{
    public DbException(String message, Exception innerEx)
    {
        super(message, innerEx);
    }
}
