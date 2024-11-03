package com.example.swh_back.Query_Support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all FAQs
    public List<FAQ> getFAQs() {
        String sql = "SELECT * FROM faq";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            FAQ faq = new FAQ();
            faq.setId(rs.getInt("id"));
            faq.setQuestion(rs.getString("question"));
            faq.setAnswer(rs.getString("answer"));
            return faq;
        });
    }
   /*  @SuppressWarnings("deprecation")
    public FAQ saveFAQ(FAQ faq) {
        String sql = "INSERT INTO faq (question, answer) VALUES (?, ?)";
        jdbcTemplate.update(sql, faq.getQuestion(), faq.getAnswer());
        // Fetch the newly created FAQ with the generated ID
        String selectSql = "SELECT * FROM faq WHERE question = ? AND answer = ?";
        return jdbcTemplate.queryForObject(selectSql, new Object[]{faq.getQuestion(), faq.getAnswer()},
            (rs, rowNum) -> {
                FAQ savedFAQ = new FAQ();
                savedFAQ.setId(rs.getInt("id"));
                savedFAQ.setQuestion(rs.getString("question"));
                savedFAQ.setAnswer(rs.getString("answer"));
                return savedFAQ;
            });
    }*/
    public void saveFAQ(String question, String answer) {
        String sql = "INSERT INTO faq (question, answer) VALUES (?, ?)";
        jdbcTemplate.update(sql,question, answer);
    }
}
