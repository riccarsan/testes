package br.com.fintechbank;

import br.com.fintechbank.repository.ClienteRepository;
import br.com.fintechbank.repository.MovimentoHistoricoRepository;
import br.com.fintechbank.repository.MovimentoRepository;
import br.com.fintechbank.service.ClienteService;
import br.com.fintechbank.service.dto.ClienteDTO;
import br.com.fintechbank.service.impl.ClienteServiceImpl;
import br.com.fintechbank.service.mapper.ClienteMapper;
import br.com.fintechbank.mock.ClienteMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

@RunWith(SpringRunner.class)
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private MovimentoRepository movimentoRepository;

    @Mock
    private MovimentoHistoricoRepository movimentoHistoricoRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @Before
    public void init() {
        this.clienteMapper = new ClienteMapper();
   //     this.clienteService = new ClienteServiceImpl();

    }

    @Test
    public void testfindAllClientes() {
        BDDMockito.when(this.clienteRepository.findAll()).thenReturn(this.clienteMapper.toEntityList(ClienteMock.listaComClientesDTO()));
        List<ClienteDTO> listCliente = this.clienteService.findAllClientes();
        assertNotNull(listCliente);
    }

    @Test
    public void testCreateCliente() {
        BDDMockito.when(this.clienteRepository.save(ArgumentMatchers.any())).thenReturn(ClienteMock.uniqueEntityCliente());
        ClienteDTO clienteDTO = this.clienteService.createCliente(ClienteMock.uniqueClienteDTO());
        assertNull(clienteDTO);
    }
}
