package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelectTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Article> list = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");

           String sql = "SELECT *";
           sql += " FROM article";
            sql += " ORDER BY id DESC";

            System.out.println(sql);

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);

            while(rs.next()) {
               int id = rs.getInt("id");
               String title = rs.getString("title");
               String body = rs.getString("body");
               Article article = new Article(id, title, body);

               list.add(article);
            }

            for(int i=0;i<list.size();i++) {
                System.out.println(list.get(i).getId());
                System.out.println(list.get(i).getTitle());
                System.out.println(list.get(i).getBody());
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

