package kr.ac.bokgpt.api.welfareapi.local.dto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "wantedDtl")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalDetailRes {

    @XmlElement(name="resultCode")
    private String resultCode;
    @XmlElement(name="resultMessage")
    private String resultMessage;
    @XmlElement(name="servId")
    private String servId;
    @XmlElement(name="servNm")
    private String servNm;
    @XmlElement(name="enfcBgngYmd")
    private String enfcBgngYmd;
    @XmlElement(name="enfcEndYmd")
    private String enfcEndYmd;
    @XmlElement(name="bizChrDeptNm")
    private String bizChrDeptNm;
    @XmlElement(name="ctpvNm")
    private String ctpvNm;
    @XmlElement(name="sggNm")
    private String sggNm;
    @XmlElement(name="servDgst")
    private String servDgst;
    @XmlElement(name="lifeNmArray")
    private String lifeNmArray;
    @XmlElement(name="trgterIndvdlNmArray")
    private String trgterIndvdlNmArray;
    @XmlElement(name="intrsThemaNmArray")
    private String intrsThemaNmArray;
    @XmlElement(name="sprtCycNm")
    private String sprtCycNm;
    @XmlElement(name="srvPvsnNm")
    private String srvPvsnNm;
    @XmlElement(name="aplyMtdNm")
    private String aplyMtdNm;
    @XmlElement(name="sprtTrgtCn")
    private String sprtTrgtCn;
    @XmlElement(name="slctCritCn")
    private String slctCritCn;
    @XmlElement(name="alwServCn")
    private String alwServCn;
    @XmlElement(name="aplyMtdCn")
    private String aplyMtdCn;
    @XmlElement(name="inqNum")
    private String inqNum;
    @XmlElement(name="lastModYmd")
    private String lastModYmd;
    @XmlElement(name="inqplCtadrList")
    private List<InqplCtadrList> inqplCtadrList;
    @XmlElement
    private List<BaslawList> baslawLists;
    @XmlElement
    private List<BasfrmList> basfrmLists;
    @XmlElement
    private List<InqplHmpgReldList> inqplHmpgReldList;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement(name = "inqplHmpgReldList")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InqplCtadrList{                 //문의처 목록
        @XmlElement
        private String wlfareInfoReldNm;
        @XmlElement
        private String wlfareInfoReldCn;
        @XmlElement
        private String wlfareInfoDtlCd;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement(name = "InqplHmpgReldList")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InqplHmpgReldList{ //관련 웹사이트 목록
        @XmlElement
        private String wlfareInfoReldNm;
        @XmlElement
        private String wlfareInfoReldCn;
        @XmlElement
        private String wlfareInfoDtlCd;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement(name = "BaslawList")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BaslawList{                     //근거 법령 목록
        @XmlElement
        private String wlfareInfoDtlCd;
        @XmlElement
        private String wlfareInfoReldNm;
        @XmlElement
        private String wlfareInfoReldCn;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement(name = "BasfrmList")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BasfrmList{                     // 서식/자료 목록
        @XmlElement
        private String wlfareInfoDtlCd;
        @XmlElement
        private String wlfareInfoReldNm;
        @XmlElement
        private String wlfareInfoReldCn;

    }
}
