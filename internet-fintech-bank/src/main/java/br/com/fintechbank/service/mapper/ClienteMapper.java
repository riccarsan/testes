package br.com.fintechbank.service.mapper;

import br.com.fintechbank.model.Cliente;
import br.com.fintechbank.model.MovimentoHistorico;
import br.com.fintechbank.service.dto.ClienteDTO;
import br.com.fintechbank.service.dto.MovimentoHistoricoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setExclusive(cliente.getExclusive());
        clienteDTO.setSaldo(cliente.getSaldo());
        clienteDTO.setNumeroConta(cliente.getNumeroConta());
        clienteDTO.setDataNascimento(cliente.getDataNascimento());

        return clienteDTO;
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteDTO.getNome());
        cliente.setExclusive(clienteDTO.getExclusive());
        cliente.setSaldo(clienteDTO.getSaldo());
        cliente.setNumeroConta(clienteDTO.getNumeroConta());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());

        return cliente;

    }

    public List<ClienteDTO> toDTOList(List<Cliente> clienteList) {

        List<ClienteDTO> clienteListaDTO = clienteList.stream()
                .map(entityHist -> new ClienteDTO(entityHist.getId(),entityHist.getNome(), entityHist.getExclusive(),
                        entityHist.getSaldo(), entityHist.getNumeroConta(), entityHist.getDataNascimento()))
                .collect(Collectors.toList());
        return clienteListaDTO;
    }

    public List<Cliente> toEntityList(List<ClienteDTO> clienteDTOList) {

        List<Cliente> clienteList = new ArrayList<Cliente>();

        for (ClienteDTO dt : clienteDTOList) {
            //     for(Cliente entity :clienteList) {
            Cliente ct = new Cliente();
            ct.setNumeroConta(dt.getNumeroConta());
            ct.setNumeroConta(dt.getNome());
            ct.setSaldo(dt.getSaldo());
            ct.setExclusive(dt.getExclusive());
            ct.setDataNascimento(dt.getDataNascimento());
            clienteList.add(ct);
        }
        //   }

        return clienteList;
    }
}
