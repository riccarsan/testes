package br.com.fintechbank.controller;

import br.com.fintechbank.exception.ClienteNotFoundException;
import br.com.fintechbank.model.Cliente;
import br.com.fintechbank.service.ClienteService;
import br.com.fintechbank.service.MovimentoService;
import br.com.fintechbank.service.dto.ClienteDTO;
import br.com.fintechbank.service.dto.MovimentoDTO;
import br.com.fintechbank.service.dto.MovimentoHistoricoDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Validated
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MovimentoService movimentoService;

    @ApiResponses(value = { @ApiResponse(code = 201, message = "201 Created success.") })
    @ApiOperation(value = "Api que adiciona um cliente.")
    @ResponseBody
    @PostMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {

        return ResponseEntity.ok(this.clienteService.createCliente(clienteDTO));

    }

    @GetMapping(value = "/clientes")
    @ApiOperation(value = "API que lista todos os clientes.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK. Success.") })
    public ResponseEntity<List<ClienteDTO>> findAllClientes() {
        return ResponseEntity.ok((this.clienteService.findAllClientes()));
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK. Success.") })
    @ApiOperation(value = "Api que faz uma operação de depósito ou saque para um cliente.")
    @ResponseBody
    @PostMapping(value = "/clientes/operacao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> operacao(@Valid @RequestBody MovimentoDTO movimentoDTO) {

        Cliente cliente = this.movimentoService.deposito(movimentoDTO);
        if(cliente == null) {
            throw new ClienteNotFoundException("Não foi encontrado o numero dessa conta"  + cliente);
        }
        if(movimentoDTO.getValorMovimento().compareTo(new BigDecimal(0)) > 0) {
            return ResponseEntity.ok("O valor depositado foi " + movimentoDTO.getValorMovimento() + ". O saldo atual é " + cliente.getSaldo());
        }
        return ResponseEntity.ok("O valor sacado foi " + movimentoDTO.getValorMovimento() + ". O saldo atual é " + cliente.getSaldo());
    }


    @GetMapping(value = "/clientes/historico")
    @ApiOperation(value = "API que busca o historico de operacoes dos correntistas.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK. Success.") })
    @ApiParam(defaultValue = "dd/MM/yyyy")
    public ResponseEntity<List<MovimentoHistoricoDTO>> findHistoryByDate(@Valid @RequestParam("data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {
        return ResponseEntity.ok((this.movimentoService.buscaHistoricoPorData(data)));
    }

}
