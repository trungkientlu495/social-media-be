package social.media.network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.payload.ResponseHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = "Input data invalid";

        if (e.getBindingResult().hasErrors()) {
            FieldError fieldError = e.getBindingResult().getFieldError();
            if (fieldError != null) {
                errorMessage = fieldError.getDefaultMessage();
            }
        }

        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_FAIL, HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(CustormException.class)
    public ResponseEntity<Object> handleUserNotFoundException(CustormException e) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_FAIL, HttpStatus.NOT_FOUND, e.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_FAIL, HttpStatus.BAD_REQUEST, "Input data is invalid");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_FAIL, HttpStatus.BAD_REQUEST, "Maximum upload size exceeded");
    }
}