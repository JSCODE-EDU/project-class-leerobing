package com.example.boardproject.service;

import com.example.boardproject.domain.Board;
import com.example.boardproject.domain.Comment;
import com.example.boardproject.domain.Member;
import com.example.boardproject.dto.CommentDto;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.repository.CommentRepository;
import com.example.boardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long save(CommentDto commentDto) {
        /*해당 게시글 조회*/
        Optional<Board> optionalBoardEntity = boardRepository.findById(commentDto.getBoardId());
        Optional<Member> optionalMember = memberRepository.findByEmail(commentDto.getMemberEmail());


        if (optionalBoardEntity.isPresent()) {
            Board board = optionalBoardEntity.get();
            Member member = optionalMember.get();
            Comment comment = Comment.toSaveEntity(commentDto, board, member);
            return commentRepository.save(comment).getId();
        } else {
            return null;
        }
    }

}
