package kr.ac.bokgpt.repository.community;

import kr.ac.bokgpt.domain.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Set<Comment>> findAllByPost_Id(Long postId);
}
