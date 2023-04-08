package kr.ac.bokgpt.welfareapi.youth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YouthReq {


    private String display="5";         //출력건수, 기본값 10, 최대 100까지 가능합니다.
    private String pageIndex ="1";      //조회할 페이지, 기본값 1
    private String srchPolicyId="";     //상세조회용



    public MultiValueMap<String,String> toMultiValueMap(){
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("display",display);
        map.add("pageIndex",pageIndex);
        return map;
    }
}
