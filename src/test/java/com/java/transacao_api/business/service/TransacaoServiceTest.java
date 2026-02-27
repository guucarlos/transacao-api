package com.java.transacao_api.business.service;


import com.java.transacao_api.business.services.EstatisticaService;
import com.java.transacao_api.business.services.TransacaoService;
import com.java.transacao_api.controller.dtos.EstatisticaResponseDTO;
import com.java.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.java.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticaResponseDTO estatistica;
    @BeforeEach
    void setup(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatistica = new EstatisticaResponseDTO(1L,20.0,20.0,20.0,20.0);
    }

    @Test
    void deveAdicionarTransacaoComSucesso(){

        transacaoService.adicionarTransacoes(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacao));

    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("Valor não pode ser menor que zero", exception.getMessage());
    }

    @Test
    void deveLimparTransacaoComSucesso(){

        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacaoDentroDoIntervalo(){

        TransacaoRequestDTO dto = new TransacaoRequestDTO(10.00,OffsetDateTime.now().minusHours(1));
        transacaoService.adicionarTransacoes(transacao);
        transacaoService.adicionarTransacoes(dto);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }


}

