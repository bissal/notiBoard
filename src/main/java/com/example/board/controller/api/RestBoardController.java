package com.example.board.controller.api;

import com.example.board.dto.BoardDto;
import com.example.board.model.entity.Board;
import com.example.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bissal on 17/01/2020.
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestBoardController {
    private BoardService boardService;

    @GetMapping("/test")
    public BoardDto test() {
        return new BoardDto();
    }

    @GetMapping("/list")
    public Page<Board> list(@PageableDefault Pageable pageable) {
        Page<Board> boardList = boardService.findBoardList(pageable);
        boardList.stream().forEach(e -> e.getContent());

        return boardList;
    }

    @PostMapping("/post")
    public Long create(BoardDto boardDto) {
        Long postId = boardService.savePost(boardDto);

        return postId;
    }

    @GetMapping("/post/{no}")
    public BoardDto read(@PathVariable("no") Long no) {
        BoardDto boardDTO = boardService.getPost(no);

        return boardDTO;
    }

    @PutMapping("/post/{no}")
    public Long update(BoardDto boardDTO) {
        Long postId = boardService.savePost(boardDTO);

        return postId;
    }

    @DeleteMapping("/post/{no}")
    public Long delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return no;
    }
}
