
package com.refCursor;
        
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import org.json.simple.JSONArray;
//import org.json.simple.JSONException;
import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Subhan-apollo
 */
public class TestResultSet {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "mehdi";
    private static final String DB_PASSWORD = "123";
    Connection dbConnection = null;

    public TestResultSet() {
        getDBConnection();
//        try {
//            callOracleStoredProcCURSORParameter();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public String callOracleStoredProcCURSORParameter()
            throws SQLException {
        String toReturn = "";

        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;

        String getDBUSERCursorSql = "{call GET_TICKETS(?,?,?)}";

        try {
//            dbConnection = getDBConnection();
            callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);

            callableStatement.setInt("P_scid", 5);
            callableStatement.registerOutParameter("p_tickets", OracleTypes.CURSOR);
            callableStatement.registerOutParameter("p_readytime", OracleTypes.CURSOR);

            // execute getDBUSERCursor store procedure
            callableStatement.execute();

            // get cursor and cast it to ResultSet
            rs = (ResultSet) callableStatement.getObject(2);

            JSONArray json = new JSONArray();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JSONObject obj = new JSONObject();

                for (int i = 1; i < numColumns + 1; i++) {
                    String column_name = rsmd.getColumnName(i);
                    switch (rsmd.getColumnType(i)) {
                        case java.sql.Types.INTEGER:
                            obj.put(column_name, rs.getInt(column_name));
                            break;
                        case java.sql.Types.NVARCHAR:
                            obj.put(column_name, rs.getNString(column_name));
                            break;
                        case java.sql.Types.VARCHAR:
                            obj.put(column_name, rs.getString(column_name));
                            break;
                        case java.sql.Types.TINYINT:
                            obj.put(column_name, rs.getInt(column_name));
                            break;
                        case java.sql.Types.DATE:
                            obj.put(column_name, rs.getDate(column_name));
                            break;
                        case java.sql.Types.TIMESTAMP:
                            obj.put(column_name, rs.getTimestamp(column_name));
                            break;
                        default:
                            obj.put(column_name, rs.getObject(column_name));
                            break;
                    }
                }
                //json.put(obj);
                json.add(obj.clone());
                
                toReturn = json.toJSONString();

                System.out.println("ID: " + rs.getInt("TICKETID"));
                System.out.println("DateTime: " + rs.getDate("DATETIME"));
                System.out.println("Priority: " + rs.getString("PRIORITY"));
                System.out.println("wcid: " + rs.getInt("WCID"));
                System.out.println("Status: " + rs.getString("STATUS"));
                System.out.println("Repetition: " + rs.getInt("REPETITION"));
                System.out.println("Agent ID: " + rs.getInt("AGENTID"));
                System.out.println("SCID: " + rs.getInt("SCID"));
                System.out.println("QUEUEID: " + rs.getInt("QUEUEID"));
                System.out.println("IDENTITYNUM: " + rs.getString("IDENTITYNUM"));

//                String userid = rs.getString("USER_ID");
//                String userName = rs.getString("USERNAME");
//                String createdBy = rs.getString("CREATED_BY");
//                String createdDate = rs.getString("CREATED_DATE");
//
//                System.out.println("UserName : " + userid);
//                System.out.println("UserName : " + userName);
//                System.out.println("CreatedBy : " + createdBy);
//                System.out.println("CreatedDate : " + createdDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return toReturn;
    }

    public void getDBConnection() {

        try {
            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            //return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
    }

//    public TestResultSet() {
//        try {
//            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe, mehdi, 123");
//            CallableStatement stmt = conn.prepareCall("{call GET_TICKETS(?,?,?)}");
//            stmt.setInt("P_scid", 4); //in parameter NUMBER
//            stmt.registerOutParameter("p_tickets", OracleTypes.CURSOR); //out parameter REF CURSOR
//            stmt.registerOutParameter("p_readytime", OracleTypes.CURSOR); //out parameter REF CURSOR
//            stmt.execute();
//            ResultSet rs = (ResultSet) stmt.getObject("p_tickets");
//            while (rs.next()) {
//                //System.out.println(rs.getString("ename") + ":" + rs.getString("empno") + ":" + rs.getString("deptno"));
//                System.out.println("ID: " + rs.getInt("TICKETID"));
//                System.out.println("DateTime: " + rs.getDate("DATETIME"));
//                System.out.println("Priority: " + rs.getString("PRIORITY"));
//                System.out.println("wcid: " + rs.getInt("WCID"));
//                System.out.println("Status: " + rs.getString("STATUS"));
//                System.out.println("Repetition: " + rs.getInt("REPETITION"));
//            }
//            rs.close();
//            rs = null;
//            stmt.close();
//            stmt = null;
//            conn.close();
//            conn = null;
//        } catch (SQLException e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//    }
//
//    public static void main(String[] args) {
//        new TestResultSet();
//    }
}
