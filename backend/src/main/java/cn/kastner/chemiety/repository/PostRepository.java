package cn.kastner.chemiety.repository;

import cn.kastner.chemiety.domain.User;
import cn.kastner.chemiety.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);
    List<Post> findByContentIsNotNullOrderByPostIdDesc();
    List<Post> findByUser(User user);
}
