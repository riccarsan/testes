package br.com.fintechbank.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class MovimentoHistoricoDTO {

    public MovimentoHistoricoDTO() {

    }
    public MovimentoHistoricoDTO(String numeroConta, String nome, String operacao, LocalDate data,
                                 BigDecimal valorMovimentado, BigDecimal saldoAtual) {
        this.numeroConta = numeroConta;
        this.nome = nome;
        this.operacao = operacao;
        this.data = data;
        this.valorMovimentado = valorMovimentado;
        this.saldoAtual = saldoAtual;
    }

    private String numeroConta;
    private String nome;
    private String operacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate data;
    private BigDecimal valorMovimentado;
    private BigDecimal saldoAtual;

}
