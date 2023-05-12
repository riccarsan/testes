package br.com.fintechbank.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(name= "movimento")
@Entity
public class Movimento {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_movimento")
    @SequenceGenerator(name = "seq_movimento", sequenceName = "seq_movimento", allocationSize = 1 )
    private Integer id;

    @Column(name= "numero_conta")
    private String numeroConta;

    @Column(name= "nome")
    private String nome;

    @Column(name= "operacao")
    private String operacao;

    @Column(name= "valor_deposito")
    private BigDecimal valorDeposito;


}
