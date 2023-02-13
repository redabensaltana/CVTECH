package dev.youcode.cvtheque.user;


import dev.youcode.cvtheque.comment.Comment;
import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.rule.Rule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "\"user\"")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String userTitle;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String tel;

    @Column
    private String linkden;

    @Column
    private String github;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "userCommentId")
    private Set<Comment> userCommentIdComments;

    @OneToOne(mappedBy = "userResumeId", fetch = FetchType.LAZY)
    private Resume userResumeId;
    @Column
    private String role;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_rule_id",
            joinColumns = @JoinColumn(name = "user_user_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private List<Rule> userRuleIdRules;

}
