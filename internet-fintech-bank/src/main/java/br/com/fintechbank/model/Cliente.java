package br.com.fintechbank.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name= "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_cliente")
    @SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1, initialValue = 12 )
    private Integer id;

    @NotNull
    @Column(name= "nome")
    private String nome;

    @Column(name= "exclusivo")
    private Boolean exclusive;

    @NotNull
    @Column(name= "saldo")
    private BigDecimal saldo;

    @NotNull
    @Column(name= "numero_conta")
    private String numeroConta;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name= "data_nascimento")
    private LocalDate dataNascimento;


}
