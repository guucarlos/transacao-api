package com.java.transacao_api.business.service;


import com.java.transacao_api.business.services.EstatisticaService;
import com.java.transacao_api.business.services.TransacaoService;
import com.java.transacao_api.controller.dtos.EstatisticaResponseDTO;
import com.java.transacao_api.controller.dtos.TransacaoRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatisticaService estatisticaService;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticaResponseDTO estatistica;
    @BeforeEach
    void setup(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatistica = new EstatisticaResponseDTO(1L,20.0,20.0,20.0,20.0);
    }

    @Test
    void calcularEstatisticaComSucesso(){
        when(transacaoService.buscarTransacoes(60))
                .thenReturn(Collections.singletonList(transacao));

        EstatisticaResponseDTO resultado = estatisticaService.calcularEstatisticaTransacoes(60);

        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatistica);
    }

    @Test
    void calcularEstatisticaQuandoListaVazia(){

        EstatisticaResponseDTO estatisticaEsperado = new EstatisticaResponseDTO(0L,0.0,0.0,0.0,0.0);

        when(transacaoService.buscarTransacoes(60))
                .thenReturn(Collections.emptyList());

        EstatisticaResponseDTO resultado = estatisticaService.calcularEstatisticaTransacoes(60);

        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaEsperado);
    }
}

