/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw06.bl;
import by.epam.lw06.bl.constant.DbConst;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Anna
 */
public class LoginLogic {
    public static boolean checkLogin(String login, String password) 
            throws DbException, InterruptedException {
        DBConnectionPool pool;
        Connection connection;
        String url = DbConst.DB_URL;
        String user = DbConst.DB_USER;
        String pass = DbConst.DB_PASSWORD;
        int maxConn = DbConst.CONNECTIONS_QUEUE_SIZE;
        try {        
            pool = DBConnectionPool.getInstance(url, user, pass, maxConn);
            connection = pool.getConnection();
        } catch (DbException e) {
            throw e;
        }
        boolean result = false;
        try {
            String sql = DbConst.SELECT_QUERY;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet data = statement.executeQuery();
                if (data.next()) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        pool.freeConnection(connection);
        pool.release();
        return result;
    }
}
