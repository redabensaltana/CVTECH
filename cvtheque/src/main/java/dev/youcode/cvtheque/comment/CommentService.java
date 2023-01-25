package dev.youcode.cvtheque.comment;

import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.user.User;
import dev.youcode.cvtheque.user.UserRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    public CommentService(final CommentRepository commentRepository,
            final UserRepository userRepository, final ResumeRepository resumeRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<CommentDTO> findAll() {
        final List<Comment> comments = commentRepository.findAll(Sort.by("commentId"));
        return comments.stream()
                .map((comment) -> mapToDTO(comment, new CommentDTO()))
                .collect(Collectors.toList());
    }

    public CommentDTO get(final Long commentId) {
        return commentRepository.findById(commentId)
                .map(comment -> mapToDTO(comment, new CommentDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CommentDTO commentDTO) {
        final Comment comment = new Comment();
        mapToEntity(commentDTO, comment);
        return commentRepository.save(comment).getCommentId();
    }

    public void update(final Long commentId, final CommentDTO commentDTO) {
        final Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(commentDTO, comment);
        commentRepository.save(comment);
    }

    public void delete(final Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private CommentDTO mapToDTO(final Comment comment, final CommentDTO commentDTO) {
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setCommentBody(comment.getCommentBody());
        commentDTO.setUserCommentId(comment.getUserCommentId() == null ? null : comment.getUserCommentId().getUserId());
        commentDTO.setResumeCommentId(comment.getResumeCommentId() == null ? null : comment.getResumeCommentId().getResumeId());
        return commentDTO;
    }

    private Comment mapToEntity(final CommentDTO commentDTO, final Comment comment) {
        comment.setCommentBody(commentDTO.getCommentBody());
        final User userCommentId = commentDTO.getUserCommentId() == null ? null : userRepository.findById(commentDTO.getUserCommentId())
                .orElseThrow(() -> new NotFoundException("userCommentId not found"));
        comment.setUserCommentId(userCommentId);
        final Resume resumeCommentId = commentDTO.getResumeCommentId() == null ? null : resumeRepository.findById(commentDTO.getResumeCommentId())
                .orElseThrow(() -> new NotFoundException("resumeCommentId not found"));
        comment.setResumeCommentId(resumeCommentId);
        return comment;
    }

}
