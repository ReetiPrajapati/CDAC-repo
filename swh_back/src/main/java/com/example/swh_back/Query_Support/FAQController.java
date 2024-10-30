
package com.example.swh_back.Query_Support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/faq")
public class FAQController {

    @Autowired
    private FAQDao faqDao;

    // Fetch all FAQs
    @GetMapping("/list")
    public ResponseEntity<List<FAQ>> getFAQs() {
        List<FAQ> faqs = faqDao.getFAQs();
        return ResponseEntity.ok(faqs);
    }



    @PostMapping("/add")
    public ResponseEntity<String> submitFAQ(

        @RequestParam String question,
        @RequestParam String answer) {
        faqDao.saveFAQ(question,answer);
        return ResponseEntity.ok("Query submitted successfully");
    }


}
