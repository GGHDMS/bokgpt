package kr.ac.bokgpt.controller.community;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.bokgpt.domain.community.constant.SearchType;
import kr.ac.bokgpt.dto.commmunity.response.PostResponseDto;
import kr.ac.bokgpt.service.community.PostPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostPageController {

    private final PostPageService postPageService;

    @Tag(name="business")
    @Operation(summary = "get Posts", description = "게시글 목록 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getPosts
            (@RequestParam(required = false) SearchType searchType,
             @RequestParam(required = false) String searchValue,
             @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.ok(postPageService.searchPosts(searchType,searchValue,pageable));
    }


//    @GetMapping("/posts/{pageNum}")
//    public ResponseEntity<Page<PostDto>> getPosts(@PathVariable Long pageNum){
//        return null;
//    }

}
