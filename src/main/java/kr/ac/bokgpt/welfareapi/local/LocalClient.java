package kr.ac.bokgpt.welfareapi.local;

import kr.ac.bokgpt.welfareapi.Client;
import kr.ac.bokgpt.welfareapi.local.dto.LocalReq;
import kr.ac.bokgpt.welfareapi.local.dto.LocalRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Slf4j
@Component
@Qualifier("localClient")
public class LocalClient implements Client<LocalRes,LocalReq> {

    @Value("${localwelfare.client.serviceKey}")
    private String serviceKey;

    @Value("${localwelfare.url}")
    private String LocalWelfareUrl;

//    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public LocalRes search(LocalReq req) {
        URI uri = UriComponentsBuilder.fromUriString(LocalWelfareUrl)
                .queryParam("serviceKey",serviceKey)
                .queryParams(req.toMultiValueMap())
                .build(true)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity httpEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<LocalRes> responseType = new ParameterizedTypeReference<LocalRes>(){};
        ResponseEntity<LocalRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

}
