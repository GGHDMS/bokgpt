package kr.ac.bokgpt.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.bokgpt.security.dto.MemberRegisterDto;
import kr.ac.bokgpt.security.service.MemberRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterContoller {

    private final MemberRegisterService memberRegisterService;

    public RegisterContoller(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @Tag(name="business_security")
    @Operation(summary = "아이디 중복체크", description = "true == 중복 , false == 중복x")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/register/idcheck")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email){
      return ResponseEntity.ok(memberRegisterService.emailCheck(email));
    }


    @Tag(name="business_security")
    @Operation(summary = "회원가입", description = "example :" +
            "{\"name\" : \"홍길동\",\n" +
            " \"email\":\"hong@gildong.com\",\n" +
            " \"gender\": \"MALE\",\n" +
            " \"locationId\":1,\n" +
            " \"lifeCycleId\":1,\n" +
            " \"password\":\"123\"\n" +
            "}\n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberRegisterDto memberRegisterDto){
        return ResponseEntity.ok(memberRegisterService.register(memberRegisterDto));
    }

}
