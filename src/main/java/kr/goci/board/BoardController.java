package kr.goci.board;

import kr.goci.commons.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kiost on 2017-06-01.
 */
@Slf4j
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createBoard(@RequestBody @Valid BoardDto.Create createDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(new ErrorResponse("bad.request", "잘못된 요청"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(modelMapper.map(boardService.createBoard(createDto), BoardDto.Detail.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getBoards(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        List<BoardDto.Summary> contents = page.getContent()
                .stream()
                .map(b -> modelMapper.map(b, BoardDto.Summary.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new PageImpl<>(contents, pageable, page.getTotalElements()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBoard(@PathVariable Long id) {
        log.info("get function : [{}]", id);
        return new ResponseEntity<>(modelMapper.map(boardService.getBoard(id), BoardDto.Detail.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBoard(@PathVariable Long id, @RequestBody @Valid BoardDto.Update updateDto, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>(new ErrorResponse("bad.request", "잘못된 요청"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(modelMapper.map(boardService.updateAccount(id, updateDto), BoardDto.Detail.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BoardNotFoundException.class)
    public ErrorResponse handleBoardNotFoundException(BoardNotFoundException e) {
        return new ErrorResponse("board.not.found.exception", "[" + e.getId() + "]에 해당하는 글이 없습니다.");
    }
}
