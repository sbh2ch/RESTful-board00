package kr.goci.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by kiost on 2017-06-01.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
