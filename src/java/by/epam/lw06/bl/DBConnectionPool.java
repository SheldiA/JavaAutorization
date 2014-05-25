/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw06.bl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//import java.util.logging.Logger;
/**
 *
 * @author Anna
 */
public class DBConnectionPool {
    private static DBConnectionPool instance;
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private String URL;
    private String user;
    private String password;
    private int maxConn;
    private BlockingQueue<Connection> freeConnections;
    //private static final Logger logger = Logger.getLogger(DBConnectionPool.class.toString());
    
    private DBConnectionPool(String URL, String user, String password, int maxConn) throws ClassNotFoundException {
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;
        Class.forName(DRIVER_NAME);
        freeConnections= new ArrayBlockingQueue(maxConn);
    }

    public static DBConnectionPool getInstance 
        (String URL, String user, String password, int maxConn) throws DbException {
        if (instance == null) {
            try {
                instance = new DBConnectionPool(URL, user, password, maxConn);
            } catch (ClassNotFoundException e) {
                throw new DbException("Driver not found", e);
            }
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException, DbException {
        Connection connection;
        if (!freeConnections.isEmpty()) {
            connection = freeConnections.take();
            try {
                if (connection.isClosed()) {
                    connection = getConnection();
                }
            }
            catch (SQLException e) {
               throw new DbException("Can't get connection", e);
            }
        } else {
            connection = newConnection();
        }
        return connection;
    }

    private Connection newConnection() throws DbException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, user, password);
        }
        catch (SQLException e) {
            throw new DbException("Can't get connection", e);
        }
        return connection;
      }

    public void freeConnection(Connection connection) throws InterruptedException {
        if (connection != null)  {
            freeConnections.put(connection);
        }
    }

    public void release() throws DbException {
        for (Connection connection : freeConnections) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                //logger.error("Can't delete connection!", e);
            }
        }
        freeConnections.clear();
    }
}
