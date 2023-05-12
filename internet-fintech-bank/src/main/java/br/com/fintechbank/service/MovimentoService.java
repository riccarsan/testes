package br.com.fintechbank.service;

import br.com.fintechbank.model.Cliente;
import br.com.fintechbank.service.dto.MovimentoDTO;
import br.com.fintechbank.service.dto.MovimentoHistoricoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MovimentoService {

    Cliente deposito(MovimentoDTO movimentoDTO);

    Cliente saque(MovimentoDTO movimentoDTO);

    List<MovimentoHistoricoDTO> buscaHistoricoPorData(LocalDate localDate);
}
