package kr.ac.bokgpt.security.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


//구현중
@Controller
public class OAuthController {

    @GetMapping("/login/google")
    @ResponseBody
    public String googleOAuthRedirect(@RequestParam String code){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/x-www-form-urlencoded");

        return null;

    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "loginForm";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "accessDenied";
    }

}
