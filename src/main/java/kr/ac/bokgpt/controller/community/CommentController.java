package kr.ac.bokgpt.controller.community;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.bokgpt.dto.commmunity.request.CommentRequest;
import kr.ac.bokgpt.dto.commmunity.request.CommentUpdateRequest;
import kr.ac.bokgpt.service.community.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @Tag(name="business")
    @Operation(summary = "upload comment", description = "댓글 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "203", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String > uploadComment(@RequestBody CommentRequest commentRequest){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(commentService.uploadComment(commentRequest))
                .toUri();
        return ResponseEntity.created(uri).body("Success");
    }

    @Tag(name="business")
    @Operation(summary = "update comment", description = "댓글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long postId,@PathVariable Long commentId,@RequestBody CommentUpdateRequest commentUpdateRequest){
        return ResponseEntity.ok(commentService.updateComment(postId,commentId,commentUpdateRequest));
    }
    @Tag(name="business")
    @Operation(summary = "delete comment", description = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(postId,commentId));
    }
}
