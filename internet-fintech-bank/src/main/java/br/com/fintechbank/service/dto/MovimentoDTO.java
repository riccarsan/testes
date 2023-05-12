package br.com.fintechbank.service.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class MovimentoDTO {

    @NotNull
    private String numeroConta;

    @NotNull
    private BigDecimal valorMovimento;

}
