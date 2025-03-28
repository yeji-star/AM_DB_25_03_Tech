package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("==프로그램 시작==");

        Scanner sc = new Scanner(System.in);

        int lastId = 0;

        while (true) {
            System.out.print("입력) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("article write")) {
                System.out.println("==글쓰기==");
                int id = lastId + 1;
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();

                lastId++;
                System.out.println(id + "번 글이 작성되었습니다");

                /// ///////

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    String sql = "insert into article";
                    sql += " SET title = '" + title + "',";
                    sql += "`body` = '" + body + "';";

                    System.out.println(sql);

                    pstmt = conn.prepareStatement(sql);

                    int affectedRows = pstmt.executeUpdate();

                    System.out.println("affected rows: " + affectedRows);

                } catch (ClassNotFoundException e) {
                    System.out.println("드라이버 로딩 실패" + e);
                } catch (SQLException e) {
                    System.out.println("에러 : " + e);
                }

            }
            else if (cmd.equals("article list")) {
                System.out.println("==글 목록==");

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                List<Article> articles = new ArrayList<>();

                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");
                    //여기서부터
                    String sql = "SELECT * ";
                    sql += " FROM article";

                    System.out.println(sql);

                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery(sql);

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String body = rs.getString("body");

                        Article article = new Article(id, title, body);

                        articles.add(article);
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

                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다.");
                    continue;
                }

                System.out.println("    번호  /   제목  ");
                for (Article article : articles) {
                    System.out.printf("     %d  /   %s  \n", article.getId(), article.getTitle());
                }

            }
            else if (cmd.startsWith("article modify")) {

                System.out.print("수정할 제목 : ");
                String newTitle = sc.nextLine();
                System.out.print("수정할 내용 : ");
                String newbody = sc.nextLine();
                int id = Integer.parseInt(cmd.split(" ")[2]);

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
                    sql += " SET title = ?, `body` = ? ";
                    sql += " WHERE id = ? ";

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
            System.out.println("==프로그램 종료==");
            sc.close();
        }
    }





