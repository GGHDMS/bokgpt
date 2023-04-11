package kr.ac.bokgpt.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.bokgpt.welfareapi.center.CenterClient;
import kr.ac.bokgpt.welfareapi.center.dto.CenterReq;
import kr.ac.bokgpt.welfareapi.center.dto.CenterRes;
import kr.ac.bokgpt.welfareapi.local.LocalClient;
import kr.ac.bokgpt.welfareapi.local.dto.LocalDetailReq;
import kr.ac.bokgpt.welfareapi.local.dto.LocalDetailRes;
import kr.ac.bokgpt.welfareapi.local.dto.LocalReq;
import kr.ac.bokgpt.welfareapi.local.dto.LocalRes;
import kr.ac.bokgpt.welfareapi.youth.YouthClient;
import kr.ac.bokgpt.welfareapi.youth.dto.YouthReq;
import kr.ac.bokgpt.welfareapi.youth.dto.YouthRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welfare")
@RequiredArgsConstructor
public class WelfareApiController {

    private final LocalClient localClient;
    private final LocalReq localReq;
    private final CenterClient centerClient;
    private final CenterReq centerReq;
    private final YouthClient youthClient;
    private final YouthReq youthReq;
    private final LocalDetailReq localDetailReq;



    @Tag(name="welfare_api")
    @Operation(summary = "get local welfarelist", description = "지자체 복지정책 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/localapi")
    public LocalRes getLocalList(){
        return localClient.localSearch(localReq);
    }


    @Tag(name="welfare_api")
    @Operation(summary = "get local welfare detail info", description = "복지정책 상세정보 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping(value = "/localdetailapi/{service_id}")
    public LocalDetailRes getLocalDetail(@PathVariable String service_id){
        localDetailReq.setServId(service_id);
        return localClient.localDetailSearch(localDetailReq);
    }


    @Tag(name="welfare_api")
    @Operation(summary = "get center welfarelist", description = "중앙부처 복지정책 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping(value = "/centerapi")
    public CenterRes getCenterList(){
        return centerClient.centerSearch(centerReq);
    }

    @Tag(name="welfare_api")
    @Operation(summary = "get youthWelfareList", description = "청년 복지정책 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/youthapi")
    public YouthRes getYouthList(){
        return  youthClient.youthSearch(youthReq);
    }

}
