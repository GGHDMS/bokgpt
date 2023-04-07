package kr.ac.bokgpt.welfareapi.youth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class YouthClient {

    @Value("${centerwelfare.client.id}")
    private static String YouthClientId;

    @Value("${centerwelfare.client.secret}")
    private static String YouthSecret;

    @Value("${centerwelfare.url.}")
    private static String YouthWelfareUrl;

}
