package br.com.fintechbank.repository;

import br.com.fintechbank.model.Movimento;
import br.com.fintechbank.model.MovimentoHistorico;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimentoHistoricoRepository extends CrudRepository<MovimentoHistorico, Long> {
    List<MovimentoHistorico> findByData(LocalDate data);
}
