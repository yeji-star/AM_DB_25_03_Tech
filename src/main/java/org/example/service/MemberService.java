package org.example.service;

import org.example.dto.Member;
import org.example.dao.MemberDao;

import java.sql.Connection;

public class MemberService {

    private MemberDao memberDao;

    public MemberService() {
        this.memberDao = new MemberDao();
    }

    public int doJoin(Connection conn, String loginId, String loginPw, String name) {
        return memberDao.doJoin(conn, loginId, loginPw, name);
    }

    public boolean isLoginIdDup(Connection conn, String loginId) {
        return memberDao.isLoginIdDup(conn, loginId);
    }

    public Member getMemberLoginId(Connection conn, String loginId) {
        return memberDao.getMemberLoginId(conn, loginId);
    }
}
