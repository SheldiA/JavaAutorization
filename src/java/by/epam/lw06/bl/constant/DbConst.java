/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.lw06.bl.constant;

/**
 *
 * @author Anna
 */
public final class DbConst {
    public static String DB_URL="jdbc:mysql://localhost/lw06";
    public static String DB_USER="root";
    public static String DB_PASSWORD="Lkz_VfqCRK_14";
    public static int CONNECTIONS_QUEUE_SIZE = 5;
    public static String SELECT_QUERY = "SELECT * FROM `user` WHERE `login` = ? AND `password` = ?";
    private DbConst(){}
}
