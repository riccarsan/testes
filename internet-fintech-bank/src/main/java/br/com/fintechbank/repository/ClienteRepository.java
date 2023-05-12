package br.com.fintechbank.repository;

import br.com.fintechbank.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

     List<Cliente> findAll();

     Cliente findByNumeroConta(String numeroConta);

}
