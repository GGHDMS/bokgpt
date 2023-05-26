package kr.ac.bokgpt.repository.community;

import kr.ac.bokgpt.domain.community.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findByTitleContaining(String title, Pageable pageable);
    Page<Post> findByContentContaining(String content,Pageable pageable);
    Page<Post> findByMember_EmailContaining(String email,Pageable pageable);
    Page<Post> findByMember_NameContaining(String name,Pageable pageable);

}
