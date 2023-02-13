package dev.youcode.cvtheque.reply;

import dev.youcode.cvtheque.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Reply {

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
    private Long replyId;

    @Column(nullable = false, columnDefinition = "text")
    private String replyBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_reply_id_id", nullable = false)
    private Comment commentReplyId;



}
