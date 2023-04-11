package kr.ac.bokgpt.welfareapi.local.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LocalRes {

    private String resultCode;
    private String resultMessage;
    private String pageNo;
    private String totalCount;
    private String numOfRows;

    private List<servList> servList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class servList{
        private String bizChrDeptNm;            //사업담당부서명
        private String ctpvNm;                  //시도명
        private String sggNm;                   //시군구명
        private String servDgst;                //서비스 요약
        private String servDtlLink;             //서비스상세링크
        private String lifeNmArray;             //생애주기명
        private String intrsThemaNmArray;       //관심주제명
        private String sprtCycNm;               //지원주기명
        private String srvPvsnNm;               //제공유형명
        private String aplyMtdNm;               //신청방법명
        private String inqNum;                  //조회수
        private String lastModYmd;              //최종수정일자
        private String servId;                  //서비스ID
        private String servNm;                  //서비스명
        private String trgterIndvdlNmArray;     //가구상황명



    }
}
