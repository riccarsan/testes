package br.com.fintechbank.service.impl;

import br.com.fintechbank.repository.ClienteRepository;
import br.com.fintechbank.service.ClienteService;
import br.com.fintechbank.service.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fintechbank.service.mapper.ClienteMapper;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper mapper;

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        return mapper.toDTO(clienteRepository.save(mapper.toEntity(clienteDTO)));
    }

    @Override
    public List<ClienteDTO> findAllClientes() {
        return clienteRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
