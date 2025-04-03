package org.example.controller;

import org.example.dto.Member;
import org.example.service.MemberService;


import java.sql.Connection;
import java.util.Scanner;

public class MemberController {
    private Connection conn;
    private Scanner sc;

private MemberService memberService;

    public MemberController(Scanner sc, Connection conn) {
        this.sc = sc;
        this.conn = conn;

        this.memberService = new MemberService();
    }

    public void doJoin() {
        String loginId = null;
        String loginPw = null;
        String loginPwConfirm = null;
        String name = null;
        System.out.println("==회원가입==");

        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();

            if (loginId.length() == 0 || loginId.contains(" ")) {
                System.out.println("아이디를 제대로 쓰지 않았음");
                continue;
            }


            boolean isLoginIdDup = memberService.isLoginIdDup(conn, loginId);

            if (isLoginIdDup) {
                System.out.println(loginId + "은(는) 이미 사용중");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine().trim();

            if (loginPw.length() == 0 || loginPw.contains(" ")) {
                System.out.println("비번을 제대로 쓰지 않았음");
                continue;
            }

            boolean loginCheckPw = true;

            while (true) {
                System.out.print("비번 확인  : ");
                loginPwConfirm = sc.nextLine().trim();

                if (loginPwConfirm.length() == 0 || loginPwConfirm.contains(" ")) {
                    System.out.println("비번 확인을 제대로 쓰지 않았음");
                    continue;
                }

                if (loginPw.equals(loginPwConfirm) == false) {
                    System.out.println("비번이 일치하지 않음");
                    loginCheckPw = false;
                }
                break;
            }
            if (loginCheckPw) {
                break;
            }
        }

        while (true) {
            System.out.print("이름 : ");
            name = sc.nextLine();

            if (name.length() == 0 || name.contains(" ")) {
                System.out.println("이름을 제대로 쓰지 않았음");
                continue;
            }
            break;
        }

        int id = memberService.doJoin(conn, loginId, loginPw, name);


        System.out.println(id + "번 회원이 가입됨");

    }

    public void login() {
        String loginId = null;
        String loginPw = null;
        System.out.println("==로그인==");

        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();

            if (loginId.length() == 0 || loginId.contains(" ")) {
                System.out.println("아이디를 제대로 쓰지 않았음");
                continue;
            }

            boolean isLoginIdDup = memberService.isLoginIdDup(conn, loginId);

            if (isLoginIdDup == false) {
                System.out.println(loginId + "은(는) 없음");
                continue;
            }
            break;
        }

        Member member = memberService.getMemberLoginId(conn, loginId);

        int tryMaxCount = 3; //시도할 수 있는 최대 횟수
        int tryCount = 0; // 시도횟수

        while (true) {
            if(tryCount >= tryMaxCount) {
                System.out.println("비번 다시 확인하십시오");
                break;
            }

            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine().trim();

            if (loginPw.length() == 0 || loginPw.contains(" ")) {
                tryCount++;
                System.out.println("비번을 제대로 쓰지 않았음");
                continue;
            }

            if(member.getLoginPw().equals(loginPw) == false) {
                tryCount++;
                System.out.println("일치하지 않음");
                continue;
            }

            System.out.println(member.getName() + "님 환영합니다");
        }

    }
}

