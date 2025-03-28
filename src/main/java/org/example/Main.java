package org.example;


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

            }


        }

        System.out.println("==프로그램 종료==");
        sc.close();
    }
}