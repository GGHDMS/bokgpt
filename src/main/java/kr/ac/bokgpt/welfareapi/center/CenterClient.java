package kr.ac.bokgpt.welfareapi.center;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class CenterClient {

    @Value("${naver.client.id}")
    private static String CenterClientId;

    @Value("${naver.client.secret}")
    private static String CenterSecret;

    @Value("${naver.url.search.local}")
    private static String CenterWelfareUrl;



}
