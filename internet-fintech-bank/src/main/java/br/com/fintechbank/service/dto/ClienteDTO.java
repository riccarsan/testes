package br.com.fintechbank.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@Api
@ApiModel(value = "ClienteDTO" , description = "Classe que representa um Cliente de um banco")
public class ClienteDTO {

    public ClienteDTO(Integer id,String nome, Boolean exclusive, BigDecimal saldo, String numeroConta, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.exclusive = exclusive;
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.dataNascimento = dataNascimento;
    }

    public ClienteDTO() {

    }

    private Integer id;
    private String nome;

    private Boolean exclusive;

    private BigDecimal saldo;

    private String numeroConta;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;
}
