package kr.ac.bokgpt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.bokgpt.db.dto.WelfareRes;
import kr.ac.bokgpt.service.WelfareGetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Service
@RestController
@RequestMapping("/welfare")
public class WelfareController {

    public final WelfareGetService welfareGetService;
    public WelfareController(WelfareGetService welfareGetService) {
        this.welfareGetService = welfareGetService;
    }

    @Tag(name="welfare_infomation")
    @Operation(summary = "get welfarelist", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{theme_id}/{page_num}")
    public WelfareRes getWelfareList(@PathVariable String theme_id , @PathVariable String page_num){
        return welfareGetService.welfareListServ();
    }


    @Tag(name="welfare_infomation")
    @Operation(summary = "get welfare", description = "복지정보의 정보를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{welfare_id}")
    public WelfareRes getWelfare(@PathVariable int welfare_id){
        WelfareRes welfareRes = null;
        return welfareGetService.welfareServ();
    }

}
