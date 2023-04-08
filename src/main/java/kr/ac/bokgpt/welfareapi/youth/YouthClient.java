package kr.ac.bokgpt.welfareapi.youth;

import kr.ac.bokgpt.welfareapi.Client;
import kr.ac.bokgpt.welfareapi.youth.dto.YouthReq;
import kr.ac.bokgpt.welfareapi.youth.dto.YouthRes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Qualifier("youthClient")
public class YouthClient implements Client<YouthRes, YouthReq> {

    @Value("${youthwelfare.client.servicekey}")
    private String openApiVlak;         //마이페이지 > OpenAPI관리 에서 발급받은 인증키

    @Value("${youthwelfare.url}")
    private String YouthWelfareUrl;     //endpoint


    @Override
    public YouthRes search(YouthReq req) {
        return null;
    }
}
