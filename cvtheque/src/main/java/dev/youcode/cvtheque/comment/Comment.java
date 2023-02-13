package dev.youcode.cvtheque.comment;


import dev.youcode.cvtheque.reply.Reply;
import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Comment {

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
    private Long commentId;

    @Column(nullable = false, columnDefinition = "text")
    private String commentBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_comment_id_id")
    private User userCommentId;

    @OneToMany(mappedBy = "commentReplyId")
    private Set<Reply> commentReplyIdReplys;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_comment_id_id", nullable = false)
    private Resume resumeCommentId;


}
