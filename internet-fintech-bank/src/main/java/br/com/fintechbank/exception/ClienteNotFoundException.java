package br.com.fintechbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Não existe cadastro para a numero da conta informada.")
public class ClienteNotFoundException extends EntityNotFoundException {

    public static ClienteNotFoundException of(String numeroConta) {
        return new ClienteNotFoundException(String.format("Não existe cadastro para a conta de número %s", numeroConta));
    }

    public static ClienteNotFoundException of() {
        return new ClienteNotFoundException("Não existe cadastro para essa conta");
    }

    private static final long serialVersionUID = 1L;

    public ClienteNotFoundException(String mensagem) {
        super(mensagem);
    }
}
