package cn.kastner.chemiety.domain;


import cn.kastner.chemiety.repository.PostRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    /**
     * 评论id 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    /**
     * 发布用户id 外键
     */
    @ManyToOne
    private User user;

    /**
     * 评论内容
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 帖子id 外键
     */
    @ManyToOne
    @JsonIgnore
    private Post post;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
