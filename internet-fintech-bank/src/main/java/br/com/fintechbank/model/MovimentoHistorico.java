package br.com.fintechbank.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(name= "movimento_historico")
@Entity
public class MovimentoHistorico {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_mov_hist")
    @SequenceGenerator(name = "seq_mov_hist", sequenceName = "seq_mov_hist", allocationSize = 1 )
    private Integer id;

    @Column(name= "numero_conta")
    private String numeroConta;

    @Column(name= "nome")
    private String nome;

    @Column(name= "operacao")
    private String operacao;

    @NotNull
    @Column(name= "data")
    private LocalDate data;

    @Column(name= "valor_movimentado")
    private BigDecimal valorMovimentado;

    @Column(name= "saldo_atual")
    private BigDecimal saldoAtual;
}
