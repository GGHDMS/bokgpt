package kr.ac.bokgpt.api.welfareapi.local;

import kr.ac.bokgpt.api.welfareapi.local.dto.LocalDetailReq;
import kr.ac.bokgpt.api.welfareapi.local.dto.LocalDetailRes;
import lombok.RequiredArgsConstructor;
import kr.ac.bokgpt.api.welfareapi.local.dto.LocalReq;
import kr.ac.bokgpt.api.welfareapi.local.dto.LocalRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocalClient{
    @Value("${localwelfare.client.serviceKey}")
    private String serviceKey;

    @Value("${localwelfare.url}")
    private String localWelfareUrl;

    @Value("${localwelfare.detailurl}")
    private String localWelfareDetailUrl;

//    private RestTemplate restTemplate = new RestTemplate();

    public LocalRes localSearch(LocalReq req) {
        URI uri = UriComponentsBuilder.fromUriString(localWelfareUrl)
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

    public LocalDetailRes localDetailSearch(LocalDetailReq req) {
        URI uri = UriComponentsBuilder.fromUriString(localWelfareDetailUrl)
                .queryParam("serviceKey",serviceKey)
                .queryParam("servId",req.getServId())
                .build(true)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity httpEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<LocalDetailRes> responseType = new ParameterizedTypeReference<LocalDetailRes>(){};
        ResponseEntity<LocalDetailRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

}
