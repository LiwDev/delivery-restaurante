package br.com.menberket.academywakanda.deliveryrestaurante.handler;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Getter
@Log4j2
public class ApiException extends RuntimeException {
    private HttpStatus statusException;
    private ErrorApiResponse bodyException;

    public ApiException(HttpStatus statusException, Exception e, String message) {
        super(message, e);
        this.statusException = statusException;
        this.bodyException = ErrorApiResponse.builder()
                .message(message)
                .description(getDescription(e))
                .build();
    }
    public static  ApiException build(HttpStatus httpStatus ,String message ,Exception exception){
        log.error("Exception: ", exception);
        return new ApiException(httpStatus,exception,message);
    }
    public static ApiException build(HttpStatus statusException, String message) {
        return new ApiException(statusException, null, message);
    }


    private String getDescription(Exception e) {
        return Optional.ofNullable(e).map(ApiException::getMessageCause).orElse(null);
    }

    private static String getMessageCause(Exception e) {
        return e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
    }

    public ResponseEntity<ErrorApiResponse> buildErrorResponseEntity() {
        return ResponseEntity
                .status(statusException)
                .body(bodyException);
    }
}
