package dev.youcode.cvtheque.hobbie;


import dev.youcode.cvtheque.resume.Resume;
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
public class Hobbie {

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
    private Long hobId;

    @Column(nullable = false)
    private String hobName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_hobbie_id_id", nullable = false)
    private Resume resumeHobbieId;


}
