package kr.ac.bokgpt.service.community;


import kr.ac.bokgpt.domain.community.constant.SearchType;
import kr.ac.bokgpt.dto.commmunity.PostDto;
import kr.ac.bokgpt.repository.community.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostPageService {

    private final PostRepository postRepository;

    public PostPageService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Page<PostDto> searchPosts(SearchType searchType , String searchValue, Pageable pageable){
        if(searchValue == null || searchValue.isBlank()){
            return postRepository.findAll(pageable).map(PostDto::from);
        }

        Page<PostDto> map = Page.empty();

        switch (searchType)
        {
            case TITLE -> {
                map=postRepository.findByTitleContaining(searchValue,pageable).map(PostDto::from);
            }
            case CONTENT -> {
                map=postRepository.findByContentContaining(searchValue,pageable).map(PostDto::from);
            }
            case NAME -> {
                map=postRepository.findByMember_NameContaining(searchValue,pageable).map(PostDto::from);
            }
            case EMAIL -> {
                map=postRepository.findByMember_EmailContaining(searchValue,pageable).map(PostDto::from);
            }
        }
        return map;
    }


}
