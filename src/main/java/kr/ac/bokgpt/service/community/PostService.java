package kr.ac.bokgpt.service.community;


import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.community.Post;
import kr.ac.bokgpt.dto.commmunity.request.PostRequest;
import kr.ac.bokgpt.dto.commmunity.response.PostWithCommentsResponseDto;
import kr.ac.bokgpt.repository.MemberRepository;
import kr.ac.bokgpt.repository.community.PostRepository;
import kr.ac.bokgpt.security.exception.MemberNotFoundException;
import kr.ac.bokgpt.security.exception.PostNotFoundException;
import kr.ac.bokgpt.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;



    public PostWithCommentsResponseDto searchPost(Long postId){
        return PostWithCommentsResponseDto.from(postRepository
                .findById(postId)
                .orElseThrow(PostNotFoundException::new));
    }

    @Transactional
    public Long uploadPost(PostRequest postRequest){
        Member member = memberRepository.findMemberByEmail(SecurityUtil.getCurrentEmail().orElseThrow(MemberNotFoundException::new));
        Post post = postRequest.toEntity(member);
        return postRepository.save(post).getId();
    }

    @Transactional
    public Long updatePost(Long postId, PostRequest postRequest){
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.updatePost(postRequest);
        return post.getId();
    }
    @Transactional
    public String deletePost(Long postId){
        try {
            postRepository.deleteById(postId);
            return "success";

        }catch (Exception e){
            throw new PostNotFoundException(e.getMessage());
        }
    }
}
