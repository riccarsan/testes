package br.com.fintechbank.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.fintechbank.model.Movimento;

public interface MovimentoRepository extends CrudRepository<Movimento, Long>  {

}
