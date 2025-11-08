package com.landingpage.landingpage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {

    @Value("${google.script.url}")
    private String googleScriptUrl;

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/submit")
    @ResponseBody
    public String submit(@RequestParam String name,
                         @RequestParam String phone,
                         @RequestParam String address) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> data = new HashMap<>();
            data.put("name", name);
            data.put("phone", phone);
            data.put("address", address);
            restTemplate.postForObject(googleScriptUrl, data, String.class);

            return """
                    <h2 style='text-align:center;'>🎉 Gửi thành công!</h2>
                    <p style='text-align:center;'>Cảm ơn bạn đã để lại thông tin.</p>
                    """;
        } catch (Exception e) {
            return "<h2 style='color:red;text-align:center;'>❌ Lỗi gửi dữ liệu: " + e.getMessage() + "</h2>";
        }
    }
}
