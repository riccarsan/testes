package br.com.fintechbank.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClienteExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({  org.springframework.dao.EmptyResultDataAccessException.class  })
    public ResponseEntity<Object> EmptyResultDataAccessException(org.springframework.dao.EmptyResultDataAccessException ex, WebRequest request) {
        String mensagem = messageSource.getMessage("conta.nao.encontrada", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, mensagem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, "valor.saque.negado", headers, HttpStatus.BAD_REQUEST, request);
    }


    public static class ErrorMessage {

        private String mensagem;


        public ErrorMessage(String mensagem){
            this.mensagem = mensagem;
        }


        public String getmensagem() {
            return mensagem;
        }

        public void setmensagem(String mensagem) {
            this.mensagem = mensagem;
        }

    }
}
