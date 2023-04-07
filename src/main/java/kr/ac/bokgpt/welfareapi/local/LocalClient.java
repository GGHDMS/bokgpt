package kr.ac.bokgpt.welfareapi.local;

import kr.ac.bokgpt.welfareapi.local.dto.LocalReq;
import kr.ac.bokgpt.welfareapi.local.dto.LocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class LocalClient{

    @Value("${localwelfare.client.serviceKey}")
    private String serviceKey;

    @Value("${localwelfare.url}")
    private String LocalWelfareUrl;



    public LocalRes localSearch(LocalReq localReq){
        URI uri = UriComponentsBuilder.fromUriString(LocalWelfareUrl)
                .queryParam("serviceKey",serviceKey)
                .queryParams(localReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<LocalRes> responseType = new ParameterizedTypeReference<LocalRes>(){};
        ResponseEntity<LocalRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                LocalRes.class
        );

        return responseEntity.getBody();

    }

}
