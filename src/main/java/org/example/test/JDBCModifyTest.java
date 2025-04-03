package org.example.test;

import java.sql.*;

import java.util.Scanner;

public class JDBCModifyTest {
    public static void main(String[] args) {

        // 있는지 없는지?

        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 제목 : ");
        String newTitle = sc.nextLine();
        System.out.print("수정할 내용 : ");
        String newbody = sc.nextLine();
        System.out.println("어디를 수정할 건가? ");
        int id = sc.nextInt();

        /// ////////

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");

            try {
                conn = DriverManager.getConnection(url, "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String sql = "UPDATE article";
            sql += " SET title = ?, ";
            sql += "`body` = ?, ";
            sql += " WHERE id = ? ";

            // if(title.length > 0) {
            //  sql += '', title= '" + title +"'";
            // }
            // if(body.length >0) {
            //  sql += ", body = '" + body + "'";
            // }
            // sql += " WHERE id = " + id + ";";


            System.out.println(sql);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newTitle);
            pstmt.setString(2, newbody);
            pstmt.setInt(3, id);

            int res = pstmt.executeUpdate();
            if (res > 0) {
                System.out.println("수정 성공");
            } else {
                System.out.println("수정 실패");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}

