package kr.ac.bokgpt.controller.community;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.bokgpt.dto.commmunity.request.PostRequest;
import kr.ac.bokgpt.dto.commmunity.response.PostWithCommentsResponse;
import kr.ac.bokgpt.service.community.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PostController {

    private  final PostService postService;

    @Tag(name="business")
    @Operation(summary = "search post", description = "게시글 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostWithCommentsResponse> searchPost(@PathVariable Long postId){
        return ResponseEntity.ok(PostWithCommentsResponse.from(postService.searchPost(postId)));
    }

    @Tag(name="business")
    @Operation(summary = "upload post", description = "게시글 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/posts")
    public ResponseEntity<String> uploadPost(@RequestBody PostRequest postRequest
    ){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(postService.uploadPost(postRequest))
                .toUri();

        return ResponseEntity.created(uri).body("Success");
    }

    @Tag(name="business")
    @Operation(summary = "delete post", description = "게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @Tag(name="business")
    @Operation(summary = "update comment", description = "게시글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest){
        postService.updatePost(postId, postRequest);
        return ResponseEntity.ok().body("Success");
    }
}
