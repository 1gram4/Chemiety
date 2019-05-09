package cn.kastner.chemiety.repository;

import cn.kastner.chemiety.domain.Comment;
import cn.kastner.chemiety.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
