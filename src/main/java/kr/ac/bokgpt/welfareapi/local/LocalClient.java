package kr.ac.bokgpt.welfareapi.local;

import org.springframework.stereotype.Component;

@Component
public class LocalClient{
//
//    @Value("${localwelfare.client.serviceKey}")
//    private String serviceKey;
//
//    @Value("${localwelfare.url}")
//    private String LocalWelfareUrl;
//
//
//
//    public LocalRes localSearch(LocalReq localReq){
//        URI uri = UriComponentsBuilder.fromUriString(LocalWelfareUrl)
//                .queryParam("serviceKey",serviceKey)
//                .queryParams(localReq.toMultiValueMap())
//                .build()
//                .encode()
//                .toUri();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_XML);
//
//        HttpEntity httpEntity = new HttpEntity<>(headers);
//        ParameterizedTypeReference<LocalRes> responseType = new ParameterizedTypeReference<LocalRes>(){};
//        ResponseEntity<LocalRes> responseEntity = new RestTemplate().exchange(
//                uri,
//                HttpMethod.GET,
//                httpEntity,
//                LocalRes.class
//        );
//
//        return responseEntity.getBody();
//
//    }

}
