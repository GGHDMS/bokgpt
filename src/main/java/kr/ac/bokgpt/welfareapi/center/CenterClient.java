package kr.ac.bokgpt.welfareapi.center;

import kr.ac.bokgpt.welfareapi.Client;
import kr.ac.bokgpt.welfareapi.center.dto.CenterReq;
import kr.ac.bokgpt.welfareapi.center.dto.CenterRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.*;
import java.net.URI;


@Component
@Qualifier("centerClient")
@Slf4j
public class CenterClient implements Client<CenterRes, CenterReq> {

    @Value("${centerwelfare.client.serviceKey}")
    private String serviceKey;

    @Value("${centerwelfare.url}")
    private String CenterWelfareUrl;


    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public CenterRes search(CenterReq req){

        URI uri = UriComponentsBuilder.fromUriString(CenterWelfareUrl)
                .queryParam("serviceKey",serviceKey)
                .queryParams(req.toMultiValueMap())
                .build(true)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity httpEntity = new HttpEntity<>(headers);
//
//        log.info("uri : {}",uri);
//        log.info("httpEntity : {}",httpEntity);

        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_XML);
            return response;
        });

        ParameterizedTypeReference<CenterRes> responseType = new ParameterizedTypeReference<CenterRes>() {};

        ResponseEntity<CenterRes> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();
    }
}
