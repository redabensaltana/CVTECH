package dev.youcode.cvtheque.education;


import dev.youcode.cvtheque.resume.Resume;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.OffsetDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Education {

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
    private Long educId;

    @Column(nullable = false)
    private String eduName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate andDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_education_id_id", nullable = false)
    private Resume resumeEducationId;



}
