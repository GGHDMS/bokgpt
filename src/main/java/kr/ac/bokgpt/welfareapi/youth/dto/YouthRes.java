package kr.ac.bokgpt.welfareapi.youth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class YouthRes {
    private int pageIndex;              //조회 페이지
    private int totalCnt;               //총건수
    private int rownum;                 //row 번호
    private String bizId;               //정책 ID
    private String polyBizSecd;         //정책일련번호
    private String polyBizTy;           //기관 및 지자체 구분
    private String polyBizSjnm;         //정책명
    private String polyItcnCn;          //정책소개
    private String plcyTpNm;            //정책유형
    private String sporScvl;            //지원규모
    private String sporCn;              //지원내용
    private String ageInfo;             //참여요건 - 연령
    private String empmSttsCn;          //참여요건 - 취업상태
    private String accrRqisCn;          //참여요건 - 학력
    private String majrRqisCn;          //참여요건 - 전공
    private String splzRlmRqisCn;       //참여요건 - 특화분야
    private String cnsgNmor;            //신청기관명
    private String rqutPrdCn;           //신청기간
    private String rqutProcCn;          //신청절차
    private String jdgnPresCn;          //심사발표
    private String rqutUrla;            //사이트 링크 주소
}
