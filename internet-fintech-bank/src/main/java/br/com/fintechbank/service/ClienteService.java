package br.com.fintechbank.service;

import br.com.fintechbank.service.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO createCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> findAllClientes();
}
