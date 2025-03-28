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

        List<Article> articles = new ArrayList<>();

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

                Article article = new Article(id, title, body);
                articles.add(article);

                lastId++;
                System.out.println(article);
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

            } else if (cmd.equals("article list")) {
                System.out.println("==글 목록==");
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다.");
                    continue;
                }

                System.out.println("    번호  /   제목  ");
                for (Article article : articles) {
                    System.out.printf("     %d  /   %s  \n", article.getId(), article.getTitle());
                }

                /// ///////
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

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

                    while(rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String body = rs.getString("body");
                        Article article = new Article(id, title, body);

                        articles.add(article);
                    }

                    for(int i=0;i<articles.size();i++) {
                        System.out.println(articles.get(i).getId());
                        System.out.println(articles.get(i).getTitle());
                        System.out.println(articles.get(i).getBody());
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
