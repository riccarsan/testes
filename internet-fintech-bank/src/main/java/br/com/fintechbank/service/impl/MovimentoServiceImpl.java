package br.com.fintechbank.service.impl;

import br.com.fintechbank.exception.ClienteNotFoundException;
import br.com.fintechbank.model.Cliente;
import br.com.fintechbank.repository.ClienteRepository;
import br.com.fintechbank.repository.MovimentoHistoricoRepository;
import br.com.fintechbank.repository.MovimentoRepository;
import br.com.fintechbank.service.MovimentoService;
import br.com.fintechbank.service.dto.MovimentoDTO;
import br.com.fintechbank.service.dto.MovimentoHistoricoDTO;
import br.com.fintechbank.service.mapper.MovimentoHistoricoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimentoServiceImpl implements MovimentoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private MovimentoHistoricoRepository movimentoHistoricoRepository;

    @Autowired
    private MovimentoHistoricoMapper movimentoHistoricoMapper;

    List<MovimentoHistoricoDTO> historicoList = new ArrayList<MovimentoHistoricoDTO>();

    @Override
    public Cliente deposito(MovimentoDTO movimentoDTO) {

        if(movimentoDTO.getValorMovimento().compareTo(new BigDecimal(0)) > 0) {
            Cliente clienteEntity = clienteRepository.findByNumeroConta(movimentoDTO.getNumeroConta());
            if (clienteEntity == null || clienteEntity.getNumeroConta() == null) {
                throw new EmptyResultDataAccessException(1);
                //         throw new ClienteNotFoundException("Não foi encontrado o numero dessa conta" + movimentoDTO.getNumeroConta());
            }
            BigDecimal saldoTotal = clienteEntity.getSaldo().add(movimentoDTO.getValorMovimento());
            clienteEntity.setSaldo(saldoTotal);
            historicoOperacao(clienteEntity, "DEPOSITO", movimentoDTO.getValorMovimento()); // alimentar tabela de historico
            return clienteEntity;
        }
        System.out.println("É um saque");
        return saque( movimentoDTO);
    }

    @Override
    public Cliente saque(MovimentoDTO movimentoDTO) {
        Cliente clienteEntity = clienteRepository.findByNumeroConta(movimentoDTO.getNumeroConta());
        if(clienteEntity == null || clienteEntity.getNumeroConta() == null) {
            throw new ClienteNotFoundException("Não foi encontrado o numero dessa conta" + movimentoDTO.getNumeroConta());
        }
        if(movimentoDTO.getValorMovimento().compareTo(clienteEntity.getSaldo()) == 1){
            throw new ClienteNotFoundException("O valor do saque é maior do que o disponível na conta" + movimentoDTO.getNumeroConta());
        }
        if(movimentoDTO.getValorMovimento().abs().compareTo(new BigDecimal(100)) <= 0){
            System.out.println("É menor do que 100");
            BigDecimal saldoTotal = clienteEntity.getSaldo().subtract(movimentoDTO.getValorMovimento().abs());
            clienteEntity.setSaldo(saldoTotal);
            historicoOperacao(clienteEntity, "SAQUE", movimentoDTO.getValorMovimento()); // alimentar tabela de historico
        }
        if(movimentoDTO.getValorMovimento().abs().compareTo(new BigDecimal(100)) > 0 &&
                movimentoDTO.getValorMovimento().abs().compareTo(new BigDecimal(300)) <= 0 ){
            System.out.println("Está entre 100 e 300");
            BigDecimal valorTaxado = movimentoDTO.getValorMovimento().multiply(new BigDecimal(0.004));
            BigDecimal valorTaxadoArredondado = valorTaxado.setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal saqueComTaxa = movimentoDTO.getValorMovimento().add(valorTaxadoArredondado);
            BigDecimal saldoTotal = clienteEntity.getSaldo().subtract(saqueComTaxa.abs());
            clienteEntity.setSaldo(saldoTotal);
            historicoOperacao(clienteEntity, "SAQUE", movimentoDTO.getValorMovimento());
        }
        if(movimentoDTO.getValorMovimento().abs().compareTo(new BigDecimal(300)) > 0 ){
            System.out.println("Está acima de 300");
            BigDecimal valorTaxado = movimentoDTO.getValorMovimento().multiply(new BigDecimal(0.01));
            BigDecimal valorTaxadoArredondado = valorTaxado.setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal saqueComTaxa = movimentoDTO.getValorMovimento().add(valorTaxadoArredondado);
            BigDecimal saldoTotal = clienteEntity.getSaldo().subtract(saqueComTaxa.abs());
            clienteEntity.setSaldo(saldoTotal);
            historicoOperacao(clienteEntity, "SAQUE", movimentoDTO.getValorMovimento());
        }
        return clienteEntity;
    }

    @Override
    public List<MovimentoHistoricoDTO> buscaHistoricoPorData(LocalDate data) {
        return movimentoHistoricoMapper.toDTOList(movimentoHistoricoRepository.findByData(data));
    }


    public List<MovimentoHistoricoDTO> historicoOperacao(Cliente cliente, String tipoOperacao, BigDecimal valorDepositado) {
        MovimentoHistoricoDTO movimentoHistoricoDTO = new MovimentoHistoricoDTO();
        movimentoHistoricoDTO.setNumeroConta(cliente.getNumeroConta());
        movimentoHistoricoDTO.setNome(cliente.getNome());
        movimentoHistoricoDTO.setOperacao(tipoOperacao);
        movimentoHistoricoDTO.setData(LocalDate.now());
        movimentoHistoricoDTO.setValorMovimentado(valorDepositado);
        movimentoHistoricoDTO.setSaldoAtual(cliente.getSaldo());
        historicoList.add(movimentoHistoricoDTO);
        movimentoHistoricoRepository.save(movimentoHistoricoMapper.toEntity(movimentoHistoricoDTO));
        return historicoList;
    }

}
