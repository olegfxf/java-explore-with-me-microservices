package ru.practicum.mainmicroservice.service.priv;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.dto.CommentMapper;
import ru.practicum.mainmicroservice.exception.BadRequestException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.model.Comment;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.model.User;
import ru.practicum.mainmicroservice.repository.CommentRepository;
import ru.practicum.mainmicroservice.repository.EventRepository;
import ru.practicum.mainmicroservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateUserCommentService {
    CommentRepository commentRepository;
    UserRepository userRepository;
    EventRepository eventRepository;

    public PrivateUserCommentService(CommentRepository commentRepository, UserRepository userRepository,
                                     EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    public CommentDto saveComment(Long userId, Long eventId, CommentDto commentDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(" Пользователь не существует"));
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Событие не существует"));
        Comment comment = CommentMapper.toComment(commentDto);
        comment.setUser(user);
        comment.setEvent(event);
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }


    public List<CommentDto> getAllCommentsByUser(Long userId, int from, int size) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(" Пользователь не существует"));
        return commentRepository.findAllByUser(user, PageRequest.of(from / size, size))
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }


    public CommentDto updateComment(Long commentId, Long userId, CommentDto commentDto) {
        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new BadRequestException("Толка автор может изменинть коментарий"));
        comment.setText(commentDto.getText());
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }


    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new BadRequestException("Толка автор может изменинть коментарий"));
        commentRepository.delete(comment);
    }
}