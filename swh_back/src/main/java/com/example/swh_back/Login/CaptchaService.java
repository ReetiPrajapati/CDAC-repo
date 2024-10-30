package com.example.swh_back.Login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {

    @Value("${recaptcha.secret.key}")
    private String secretKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean verifyCaptcha(String captchaResponse) {
        System.out.println("CAPTCHA response: " + captchaResponse);
        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey + "&response=" + captchaResponse;
        ResponseEntity<ReCaptchaResponse> responseEntity = restTemplate.postForEntity(url, null,
                ReCaptchaResponse.class);
        return responseEntity.getBody() != null && responseEntity.getBody().isSuccess();
    }

    private static class ReCaptchaResponse {
        private boolean success;

        public boolean isSuccess() {
            return success;
        }
    }
}
