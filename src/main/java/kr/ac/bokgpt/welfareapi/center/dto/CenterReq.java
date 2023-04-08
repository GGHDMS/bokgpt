package kr.ac.bokgpt.welfareapi.center.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CenterReq {

    private String pageNo="1";
    private String numOfRows="5";
    private String srchKeyCode="003";
    private String callTp ="L";



    public MultiValueMap<String,String> toMultiValueMap(){
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("pageNo",pageNo);
        map.add("numOfRows",numOfRows);
        map.add("srchKeyCode",srchKeyCode);
        map.add("callTp",callTp);
        return map;
    }
}
