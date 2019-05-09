package cn.kastner.chemiety.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    public static final String CUR_USER = "currentUser";

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Role {
        TEACHER,
        STUDENT
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public enum Gender {
        MALE,
        FEMALE
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;

    @JsonIgnore
    @Column(columnDefinition = "TEXT")
    private String password;

    private Gender gender;

    private Role roles;

    private String name;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Post> posts;

    public User () {

    }

    public User (String name, String password) {
        this.username = name;
        this.password = password;
    }

    public User (String name) {
        this.roles = Role.STUDENT;
        this.gender = Gender.MALE;
        this.name = name;
    }




}
