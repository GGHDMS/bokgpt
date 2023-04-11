package kr.ac.bokgpt.welfareapi.center.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CenterRes {


    private String totalCount;
    private String pageNo;
    private String numOfRows;
    private String resultCode;
    private String resultMessage;
    private List<ServList> servList;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServList{
        private String inqNum;                      //서비스ID
        private String jurMnofNm;                      //서비스명
        private String jurOrgNm;
        private String servDgst;                   //소관부처명
        private String servDtlLink;                    //소관조직명
        private String servId;                      //조회수
        private String servNm;                    //서비스 요약
        private String svcfrstRegTs;                 //서비스상세링크
        private String trgterIndvdlArray;                //서비스등록일

    }
}
