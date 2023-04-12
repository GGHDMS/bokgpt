package kr.ac.bokgpt.api.welfareapi.youth;


import lombok.RequiredArgsConstructor;
import kr.ac.bokgpt.api.welfareapi.youth.dto.YouthReq;
import kr.ac.bokgpt.api.welfareapi.youth.dto.YouthRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class YouthClient{


    @Value("${youthwelfare.client.servicekey}")
    private String openApiVlak;         //마이페이지 > OpenAPI관리 에서 발급받은 인증키

    @Value("${youthwelfare.url}")
    private String YouthWelfareUrl;     //endpoint

//    @Value("${centerwelfare.client.id}")
//    private static String YouthClientId;
//
//    @Value("${centerwelfare.client.secret}")
//    private static String YouthSecret;
//
//    @Value("${centerwelfare.url.}")
//    private static String YouthWelfareUrl;

    public YouthRes youthSearch(YouthReq req) {
        return null;
    }
}