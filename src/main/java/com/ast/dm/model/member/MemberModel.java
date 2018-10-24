package com.ast.dm.model.member;

import com.ast.dm.model.topic.TopicModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class MemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String name = "";
    private String password;
    private String role = "";

    /* Si borro un member, entonces borro sus topics asociados */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    private Set<TopicModel> topics = new HashSet<>();

    public MemberModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MemberModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<TopicModel> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicModel> topics) {
        this.topics = topics;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override public String toString() {
        return "MemberModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
