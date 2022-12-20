package br.com.banco.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idTransferencia;

    @Column(name = "data_transferencia")
    private ZonedDateTime dataTransferencia = ZonedDateTime.ofInstant(Instant.now()
        , ZoneId.of("America/Sao_Paulo"));
    
    @Column(precision = 20, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(length = 15, nullable = false)
    private String tipo;

    @Column(name = "nome_operador_transacao", length = 50)
    private String nomeOperador;

    @ManyToOne()
    private Conta conta;
}
