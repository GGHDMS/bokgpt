package kr.ac.bokgpt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ac.bokgpt.dto.request.MemberRequest;
import kr.ac.bokgpt.dto.response.Member.MemberResponse;
import kr.ac.bokgpt.dto.response.classification.ClassificationResponse;
import kr.ac.bokgpt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Tag(name="business_memberInfo")
    @Operation(summary = "회원 정보", description = "회원 정보를 가져오는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/members/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberResponse> searchMemberInfo() {
        return ResponseEntity.ok(memberService.searchMemberInfo());
    }

    @Tag(name="business_memberInfo")
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정하는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PutMapping("/members/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> updateMemberInfo(@Valid @RequestBody MemberRequest memberRequest) {
        memberService.updateMemberInfo(memberRequest);
        return ResponseEntity.ok("Success");
    }

    @Tag(name="business_memberInfo")
    @Operation(summary = "회원 맞춤 검색 조건", description = "회원의 맞춤형 검색 조건을 가져오는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/members/me/classification")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ClassificationResponse> searchMemberClassification() {
        return ResponseEntity.ok(memberService.searchMemberClassification());
    }
}
