package com.java.transacao_api.business.services;


import com.java.transacao_api.controller.dtos.EstatisticaResponseDTO;
import com.java.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    public final TransacaoService transacaoService;

    public EstatisticaResponseDTO calcularEstatisticaTransacoes(Integer intervalorBusca){

        log.info("Iniciada a busca de estatistica de transações pelo periodo de tempo " + intervalorBusca);

        long start = System.currentTimeMillis();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervalorBusca);

        if (transacoes.isEmpty()){
            return new EstatisticaResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        long finish = System.currentTimeMillis();
        long tempoRequisicao = finish - start;

        System.out.println("Tempo de requisicao: " + tempoRequisicao + "Mili");

        log.info("Estatistisca retornado com sucesso");

        return new EstatisticaResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
