package dev.youcode.cvtheque.resume;


import dev.youcode.cvtheque.comment.Comment;
import dev.youcode.cvtheque.education.Education;
import dev.youcode.cvtheque.experience.Experience;
import dev.youcode.cvtheque.hobbie.Hobbie;
import dev.youcode.cvtheque.language.Language;
import dev.youcode.cvtheque.project.Project;
import dev.youcode.cvtheque.skill.Skill;
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
public class Resume {

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
    private Long resumeId;

    @OneToMany(mappedBy = "resumeExperienceId")
    private Set<Experience> resumeExperienceIdExperiences;

    @OneToOne
    @JoinColumn(name = "user_resume_id_id", nullable = false)
    private User userResumeId;

    @OneToMany(mappedBy = "resumeEducationId")
    private Set<Education> resumeEducationIdEducations;

    @OneToMany(mappedBy = "resumeProjectId")
    private Set<Project> resumeProjectIdProjects;

    @OneToMany(mappedBy = "resumeSkillId")
    private Set<Skill> resumeSkillIdSkills;

    @OneToMany(mappedBy = "resumeLanguageId")
    private Set<Language> resumeLanguageIdLanguages;

    @OneToMany(mappedBy = "resumeHobbieId")
    private Set<Hobbie> resumeHobbieIdHobbies;

    @OneToMany(mappedBy = "resumeCommentId")
    private Set<Comment> resumeCommentIdComments;



}
