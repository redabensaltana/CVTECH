package dev.youcode.cvtheque.reply;

import dev.youcode.cvtheque.comment.Comment;
import dev.youcode.cvtheque.comment.CommentRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    public ReplyService(final ReplyRepository replyRepository,
            final CommentRepository commentRepository) {
        this.replyRepository = replyRepository;
        this.commentRepository = commentRepository;
    }

    public List<ReplyDTO> findAll() {
        final List<Reply> replys = replyRepository.findAll(Sort.by("replyId"));
        return replys.stream()
                .map((reply) -> mapToDTO(reply, new ReplyDTO()))
                .collect(Collectors.toList());
    }

    public ReplyDTO get(final Long replyId) {
        return replyRepository.findById(replyId)
                .map(reply -> mapToDTO(reply, new ReplyDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final ReplyDTO replyDTO) {
        final Reply reply = new Reply();
        mapToEntity(replyDTO, reply);
        return replyRepository.save(reply).getReplyId();
    }

    public void update(final Long replyId, final ReplyDTO replyDTO) {
        final Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(replyDTO, reply);
        replyRepository.save(reply);
    }

    public void delete(final Long replyId) {
        replyRepository.deleteById(replyId);
    }

    private ReplyDTO mapToDTO(final Reply reply, final ReplyDTO replyDTO) {
        replyDTO.setReplyId(reply.getReplyId());
        replyDTO.setReplyBody(reply.getReplyBody());
        replyDTO.setCommentReplyId(reply.getCommentReplyId() == null ? null : reply.getCommentReplyId().getCommentId());
        return replyDTO;
    }

    private Reply mapToEntity(final ReplyDTO replyDTO, final Reply reply) {
        reply.setReplyBody(replyDTO.getReplyBody());
        final Comment commentReplyId = replyDTO.getCommentReplyId() == null ? null : commentRepository.findById(replyDTO.getCommentReplyId())
                .orElseThrow(() -> new NotFoundException("commentReplyId not found"));
        reply.setCommentReplyId(commentReplyId);
        return reply;
    }

}
