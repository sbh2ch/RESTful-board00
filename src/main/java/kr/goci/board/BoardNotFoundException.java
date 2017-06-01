package kr.goci.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by kiost on 2017-06-01.
 */
@Getter
@AllArgsConstructor
public class BoardNotFoundException extends RuntimeException {
    private Long id;
}
