package br.com.fintechbank.mock;

import br.com.fintechbank.model.Cliente;
import br.com.fintechbank.service.dto.ClienteDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ClienteMock {

    public static Cliente uniqueEntityCliente() {

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setSaldo(new BigDecimal(1000.00));
        cliente.setNome("Pedro Alvares Cabral");
        cliente.setExclusive(false);
        cliente.setNumeroConta("100021");
        cliente.setDataNascimento(LocalDate.now());
        return cliente;
    }

    public static ClienteDTO uniqueClienteDTO() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1);
        clienteDTO.setSaldo(new BigDecimal(1000.00));
        clienteDTO.setNome("Pedro Alvares Cabral");
        clienteDTO.setExclusive(false);
        clienteDTO.setNumeroConta("100021");
        clienteDTO.setDataNascimento(LocalDate.now());
        return clienteDTO;
    }

    public static List<ClienteDTO> listaComClientesDTO() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setSaldo(new BigDecimal(100.00));
        clienteDTO.setNome("Pedro Alvares Cabral");
        clienteDTO.setDataNascimento(LocalDate.now());
        clienteDTO.setExclusive(true);
        clienteDTO.setNumeroConta("100021");

        List<ClienteDTO> listaCliente = Arrays.asList(clienteDTO);
        return listaCliente;
    }

    public static List<Cliente> listaComClientes() {

        Cliente cliente = new Cliente();
        cliente.setSaldo(new BigDecimal(100.00));
        cliente.setNome("Pedro Alvares Cabral");
        cliente.setDataNascimento(LocalDate.now());
        cliente.setExclusive(true);
        cliente.setNumeroConta("100021");

        List<Cliente> listaCliente = Arrays.asList(cliente);
        return listaCliente;
    }
}
