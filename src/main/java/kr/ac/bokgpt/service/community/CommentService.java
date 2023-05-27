package kr.ac.bokgpt.service.community;

import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.community.Comment;
import kr.ac.bokgpt.domain.community.Post;
import kr.ac.bokgpt.dto.commmunity.request.CommentRequest;
import kr.ac.bokgpt.dto.commmunity.request.CommentUpdateRequest;
import kr.ac.bokgpt.repository.MemberRepository;
import kr.ac.bokgpt.repository.community.CommentRepository;
import kr.ac.bokgpt.repository.community.PostRepository;
import kr.ac.bokgpt.security.exception.CommentNotFoundException;
import kr.ac.bokgpt.security.exception.MemberNotFoundException;
import kr.ac.bokgpt.security.exception.PostNotFoundException;
import kr.ac.bokgpt.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Long uploadComment(CommentRequest commentRequestDto){
        Member member = memberRepository.findMemberByEmail(SecurityUtil.getCurrentEmail().orElseThrow(MemberNotFoundException::new));
        Post post = postRepository.getReferenceById(commentRequestDto.postId());
        Comment comment= commentRepository.save(commentRequestDto.toEntity(member, post));

        if(comment.getParentCommentId() != null){
            Comment parentComment = commentRepository.getReferenceById(comment.getParentCommentId());
            parentComment.getChildComments().add(comment);
        }
        return comment.getId();
    }
    @Transactional
    public String updateComment(Long postId,Long commentId,CommentUpdateRequest commentUpdateRequest)
    {
        Post post=postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.updateComment(commentUpdateRequest);
        commentRepository.save(comment);
        return "success";
    }
    @Transactional
    public String deleteComment(Long postId,Long commentId){
        try {
            postRepository.getReferenceById(postId);
            commentRepository.deleteById(commentId);
            return "success";

        }catch (Exception e){
            throw new CommentNotFoundException(e.getMessage());
        }
    }


}
