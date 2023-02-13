package dev.youcode.cvtheque.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.youcode.cvtheque.resume.Resume;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public Comment findCommentByResumeCommentId(Resume resumeCommentId);

}
