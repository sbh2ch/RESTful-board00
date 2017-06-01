package kr.goci.board;

import kr.goci.commons.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by kiost on 2017-06-01.
 */
@RestController("/api/boards")
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

        return new ResponseEntity<>(modelMapper.map(boardService.createBoard(createDto), BoardDto.Detail.class), HttpStatus.OK);
    }

}
