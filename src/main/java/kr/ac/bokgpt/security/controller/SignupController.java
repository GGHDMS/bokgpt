package kr.ac.bokgpt.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ac.bokgpt.dto.MemberDto;
import kr.ac.bokgpt.security.dto.signupDto;
import kr.ac.bokgpt.security.service.MemberSignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class SignupController {
    private final MemberSignupService memberSignupService;

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
    @PostMapping("/members")
    public ResponseEntity<String> signup(
            @Valid @RequestBody signupDto signupDto
    ) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberSignupService.signup(signupDto))
                .toUri();
        return ResponseEntity.created(location).body("Success");
    }

    @Tag(name="business_security")
    @Operation(summary = "아이디 중복체크", description = "true == 중복 , false == 중복x")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/members/idCheck")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email){
        return ResponseEntity.ok(memberSignupService.emailCheck(email));
    }

    @Tag(name="business_security")
    @Operation(summary = "myInfo", description = "내 정보")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })

//    @GetMapping("/members/me")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<MemberDto> getMyMemberInfo() {
//        return ResponseEntity.ok(memberSignupService.getMyMemberWithAuthorities());
//    }

    @GetMapping("/members/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDto> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberSignupService.getMemberWithAuthorities(email));
    }
}