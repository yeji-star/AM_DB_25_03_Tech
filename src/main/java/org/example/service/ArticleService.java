package org.example.service;

import org.example.dto.Article;
import org.example.dao.ArticleDao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleService {

    private  ArticleDao articleDao;

    public ArticleService(Connection conn) {
        this.articleDao = new ArticleDao(conn);
    }

    public int doWrite(String title, String body) {
        return articleDao.doWrite(title, body);

    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }


    public Map<String, Object> getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void doModify(int id, String title, String body) {
        articleDao.doModify(id, title, body);
    }

    public void doDelete(int id) {
        articleDao.doDelete(id);
    }
}
