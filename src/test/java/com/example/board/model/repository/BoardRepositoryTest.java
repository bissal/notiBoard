package com.example.board.model.repository;

import com.example.board.BoardApplicationTests;
import com.example.board.dto.BoardDto;
import com.example.board.model.entity.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by bissal on 17/01/2020.
 */
public class BoardRepositoryTest extends BoardApplicationTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void create() {
        // given
        BoardDto boardDTO = BoardDto.builder()
                .title("title")
                .content("content")
                .writer("writer")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        // when
        Board save = boardRepository.save(boardDTO.toEntity());

        // then
        Assertions.assertNotNull(save);
    }

    @Test
    @Transactional
    public void read() {
        // given
        Long id = 1L;

        // when
        Optional<Board> post = boardRepository.findById(id);

        // then
        Assertions.assertTrue(post.isPresent());
    }

    @Test
    public void update() {
        // given
        Optional<Board> post = boardRepository.findById(1L);

        // when
        post.ifPresent(selected -> {
            BoardDto boardDTO = BoardDto.builder()
                    .id(selected.getId())
                    .title(selected.getTitle())
                    .content(selected.getContent())
                    .writer(selected.getWriter())
                    .createdDate(selected.getCreatedDate())
                    .modifiedDate(selected.getLastModifiedDate())
                    .build();
            boardDTO.setContent("modified.");
            boardDTO.setModifiedDate(LocalDateTime.now());

            Board save = boardRepository.save(boardDTO.toEntity());

            // then
            Assertions.assertEquals(boardDTO.getContent(), save.getContent());
        });
    }

    @Test
    @Transactional
    public void delete() {
        // given
        Optional<Board> post = boardRepository.findById(1L);
        Assertions.assertTrue(post.isPresent());

        // when
        post.ifPresent(selected -> {
            boardRepository.delete(selected);
        });

        // then
        Optional<Board> deleted = boardRepository.findById(1L);
        Assertions.assertFalse(deleted.isPresent());
    }
}