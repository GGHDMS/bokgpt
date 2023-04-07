package kr.ac.bokgpt.db.dto;


import lombok.Data;

@Data
public class Welfare {
    private String welfareName;
    private int welfareId;
    private String enfcBgngYmd;
    private String bizChrDeptNm;
    private String lifeNmArray;
    private String trgterIndvdlNmArray;
    private String intrsThemaNmArray;
    private String sprtCycNm;
    private String srvPvsnNm;
    private String aplyMtdNm;
    private String ctpvNm;
    private String sggNm;
    private String servDgst;
    private String aplyMtdCn;
    private String sprtTrgtCn;
    private String slctCritCn;
    private String alwServCn;
    private String lastModYmd;
    private String servDtlLink;

}
