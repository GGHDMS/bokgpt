package kr.ac.bokgpt.welfareapi.local.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LocalReq {

    private String pageNo="1";
    private String numOfRows="5";



    public MultiValueMap<String,String> toMultiValueMap(){
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("pageNo",pageNo);
        map.add("numOfRows",numOfRows);
        return map;
    }
}
