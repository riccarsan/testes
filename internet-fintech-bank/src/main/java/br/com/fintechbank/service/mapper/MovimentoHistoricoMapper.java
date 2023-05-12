package br.com.fintechbank.service.mapper;

import br.com.fintechbank.model.Movimento;
import br.com.fintechbank.model.MovimentoHistorico;
import br.com.fintechbank.service.dto.MovimentoDTO;
import br.com.fintechbank.service.dto.MovimentoHistoricoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovimentoHistoricoMapper {

    public MovimentoHistorico toEntity(MovimentoHistoricoDTO movimentoHistoricoDTO) {

        MovimentoHistorico movimentoHistorico = new MovimentoHistorico();
        movimentoHistorico.setNumeroConta(movimentoHistoricoDTO.getNumeroConta());
        movimentoHistorico.setNome(movimentoHistoricoDTO.getNome());
        movimentoHistorico.setOperacao(movimentoHistoricoDTO.getOperacao());
        movimentoHistorico.setData(movimentoHistoricoDTO.getData());
        movimentoHistorico.setValorMovimentado(movimentoHistoricoDTO.getValorMovimentado());
        movimentoHistorico.setSaldoAtual(movimentoHistoricoDTO.getSaldoAtual());

        return movimentoHistorico;
    }

    public MovimentoHistoricoDTO toDTO(MovimentoHistorico movimentoHistorico) {

        MovimentoHistoricoDTO movimentoHistoricoDTO = new MovimentoHistoricoDTO();
        movimentoHistoricoDTO.setNumeroConta(movimentoHistorico.getNumeroConta());
        movimentoHistoricoDTO.setNome(movimentoHistorico.getNome());
        movimentoHistoricoDTO.setOperacao(movimentoHistorico.getOperacao());
        movimentoHistoricoDTO.setData(movimentoHistorico.getData());
        movimentoHistoricoDTO.setValorMovimentado(movimentoHistorico.getValorMovimentado());
        movimentoHistoricoDTO.setSaldoAtual(movimentoHistorico.getSaldoAtual());
        return movimentoHistoricoDTO;
    }

    public List<MovimentoHistoricoDTO> toDTOList(List<MovimentoHistorico> movimentoHistoricoList) {

        List<MovimentoHistoricoDTO> movHisDTO = movimentoHistoricoList.stream()
                .map(entityHist -> new MovimentoHistoricoDTO(entityHist.getNumeroConta(),entityHist.getNome(),
                        entityHist.getOperacao(), entityHist.getData(), entityHist.getValorMovimentado(), entityHist.getSaldoAtual() ))
                .collect(Collectors.toList());
        return movHisDTO;
    }
}
