/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author yibe1
 */
public class Repo {

    private String dbname = "cl";
    private String sec = ".[TzO1abD8tb[B)0";
    private String user = "admin2";
    private java.util.Date dt;
    private String date, ip;

    Connection conn = null;
    Statement stmt = null;
    private BufferedReader br;
    private boolean success;

    public Repo(String ip) throws IOException {

        this.ip = ip;
        System.out.println("here ip = " + ip);
        dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(dt);
    }

    ArrayList<String[]> getWaiting() {
        System.out.println("h.....................");
        ArrayList<String[]> list = new ArrayList<>();
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + dbname, user, sec);
            stmt = (Statement) conn.createStatement();
            String data = "select p.student_id, `full_name`,`room`,`allocated_user`,`status`, TIMEDIFF(NOW(),`t1`) as t from `waiting_patients` w join patient p on w.student_id = p.student_id where date = '" + date + "' order by t1 desc";
            ResultSet rs = stmt.executeQuery(data);
            System.out.println("sql=== " + data);
            while (rs.next()) {
                String[] message = new String[5];
                message[0] = rs.getString("full_name");
                message[1] = String.valueOf(rs.getInt("room"));
                message[2] = rs.getString("allocated_user");
                message[3] = rs.getString("t");
                message[4] = String.valueOf(rs.getInt("status"));
////            System.out.println("status......and...time diff.." + message[4] + ", " + message[3]);
                list.add(message);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("error " + ex);
            JOptionPane.showMessageDialog(null, "Error " + ex);
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
        }
        return list;
    }
 

//    ArrayList<ArrayList<String>> getWaiting2() {
//        ArrayList<ArrayList<String>> patient_list = new ArrayList<ArrayList<String>>();
//        ArrayList<String> list = new ArrayList<>();
//        try {
//            stmt = (Statement) conn.createStatement();
//            String data = "select * from `waiting_patients` w left join patient p on w.student_id = p.student_id where w.status = 0 and date = '" + date + "'";
//            ResultSet rs = stmt.executeQuery(data);
//
//            while (rs.next()) {
//                list.add(rs.getString("student_id"));
//                list.add(rs.getString("first_name"));
//                list.add(rs.getString("second_name"));
//                patient_list.add(list);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (stmt != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//            }
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return patient_list;
//    }
    ArrayList<ArrayList<String>> getMyWaiting2(String userId) {
        ArrayList<ArrayList<String>> patient_list = new ArrayList<ArrayList<String>>();

        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + dbname, user, sec);
            stmt = (Statement) conn.createStatement();
            String data = "select * from `waiting_patients` w join patient p on w.student_id = p.student_id where w.status = 1 and date = '" + date + "' and complete = 0 and allocated_user = '" + userId + "'order by date";
            ResultSet rs = stmt.executeQuery(data);

            while (rs.next()) {
                ArrayList<String> list = new ArrayList<>();
                list.add(rs.getString("student_id"));
                list.add(rs.getString("full_name"));
                list.add(rs.getString("date"));
                patient_list.add(list);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return patient_list;
    }

}
