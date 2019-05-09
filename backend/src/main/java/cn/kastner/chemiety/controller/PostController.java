package cn.kastner.chemiety.controller;

import cn.kastner.chemiety.domain.Comment;
import cn.kastner.chemiety.domain.Post;
import cn.kastner.chemiety.domain.User;
import cn.kastner.chemiety.repository.CommentRepository;
import cn.kastner.chemiety.repository.PostRepository;
import cn.kastner.chemiety.repository.UserRepository;
import cn.kastner.chemiety.util.NetResult;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.nio.ch.Net;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NetResult netResult;

    @Autowired
    public PostController(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, NetResult netResult) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.netResult = netResult;
    }

    /**
     * @param post    {
     *                title: 标题
     *                content： 内容
     *                }
     * @param session 会话
     * @return {
     * 0：发表成功
     * -1：帖子内容为空
     * -2：用户登陆状态过期或未登录
     * }
     */
    @PostMapping(value = "/admin/posts")
    public NetResult createPost(Post post, HttpSession session) {
        User user = (User) session.getAttribute(User.CUR_USER);
        if (user != null) {
            if (post.getContent() != null &&
                    post.getTitle() != null) {
                post.setCreateDate(new Date());
                post.setUser(user);
                postRepository.save(post);
                netResult.status = 0;
                netResult.result = post;
            } else {
                netResult.status = -1;
                netResult.result = "帖子内容/主题为空";
            }
        } else {
            netResult.status = -2;
            netResult.result = "登陆状态过期，请重新登录！";
        }
        return netResult;
    }

    @PostMapping(value = "/posts")
    public NetResult createStuPost(@RequestParam String title, @RequestParam String content, @RequestParam String username) {
        User user = userRepository.save(new User(username));
        Post post = new Post();
        post.setContent(content);
        post.setTitle(title);
        if (post.getContent() != null &&
                post.getTitle() != null) {
            post.setCreateDate(new Date());
            post.setUser(user);
            postRepository.save(post);
            netResult.status = 0;
            netResult.result = post;
        } else {
            netResult.status = -1;
            netResult.result = "帖子内容/主题为空";
        }
        return netResult;
    }

    @PostMapping(value = "/posts/{id}/comments")
    public NetResult createStuComment(@RequestParam String content, @PathVariable Long id, @RequestParam String username) {
        User user = userRepository.save(new User(username));
        Post exPost = postRepository.findByPostId(id);
        Comment comment = new Comment();
        comment.setContent(content);
        if (comment.getContent() == null) {
            netResult.status = -2;
            netResult.result = "评论内容为空";
        } else if (exPost == null) {
            netResult.status = -1;
            netResult.result = "评论对象异常";
        } else {
            comment.setUser(user);
            comment.setCreateDate(new Date());
            int commentNumber = exPost.getCommentNumber();
            exPost.setCommentNumber(++commentNumber);
            postRepository.save(exPost);
            comment.setPost(exPost);
            commentRepository.save(comment);
            netResult.status = 0;
            netResult.result = comment;
        }
        return netResult;
    }

    /**
     * @param comment {
     *                content:内容
     *                }
     * @param session 会话
     * @return {
     * 0: 评论成功
     * -1: 评论内容为空
     * -2: 未登录或登陆状态过期
     * }
     */
    @PostMapping(value = "/admin/posts/{id}/comments")
    public NetResult createComment(Comment comment, @PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute(User.CUR_USER);
        if (user != null) {
            Post exPost = postRepository.findByPostId(id);
            if (comment.getContent() == null) {
                netResult.status = -2;
                netResult.result = "评论内容为空";
            } else if (exPost == null) {
                netResult.status = -1;
                netResult.result = "评论对象异常";
            } else {
                comment.setUser(user);
                comment.setCreateDate(new Date());
                int commentNumber = exPost.getCommentNumber();
                exPost.setCommentNumber(++commentNumber);
                postRepository.save(exPost);
                comment.setPost(exPost);
                commentRepository.save(comment);
                netResult.status = 0;
                netResult.result = comment;
            }
        }
        return netResult;
    }

    @GetMapping(value = "/posts/{id}")
    public NetResult getPost(@PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            netResult.status = 0;
            netResult.result = postOptional.get();
        } else {
            netResult.status = -1;
            netResult.result = "没有这个帖子";
        }
        return netResult;
    }

    @GetMapping("/posts")
    public NetResult getPosts() {
        List<Post> posts = postRepository.findByContentIsNotNullOrderByPostIdDesc();
        for (Post post : posts) {
            post.setComments(commentRepository.findByPost(post));
        }
        netResult.status = 0;
        netResult.result = posts;
        return netResult;
    }

    @RequestMapping(value = "/admin/posts/getMyPosts")
    public NetResult getMyPosts(HttpSession session) {
        User user = (User) session.getAttribute(User.CUR_USER);
        if (user != null) {
            List<Post> posts = postRepository.findByUser(user);
            for (Post post : posts) {
                post.setComments(commentRepository.findByPost(post));
            }
            netResult.status = 0;
            netResult.result = posts;
        } else {
            netResult.status = -2;
            netResult.result = "登陆状态过期，请重新登录！";
        }
        return netResult;
    }

    @DeleteMapping(value = "/admin/posts/{id}")
    public NetResult deletePost(HttpSession session, @PathVariable Long id) {
        User user = (User) session.getAttribute(User.CUR_USER);
        if (user != null) {
            if (user.getRoles() == User.Role.TEACHER) {
                Post post = postRepository.findByPostId(id);
                if (post != null) {
                    postRepository.delete(post);
                    netResult.status = 0;
                    netResult.result = "删除成功！";
                } else {
                    netResult.status = -1;
                    netResult.result = "参数错误！";
                }
            } else {
                netResult.status = -2;
                netResult.result = "无权限！";
            }
        } else {
            netResult.status = -3;
            netResult.result = "登录状态过期，请重新登录！";
        }
        return netResult;
    }

}
