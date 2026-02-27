package com.java.transacao_api.controller;

import com.java.transacao_api.business.services.EstatisticaService;
import com.java.transacao_api.controller.dtos.EstatisticaResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    EstatisticaController estatisticaController;

    @Mock
    EstatisticaService estatisticaService;

    EstatisticaResponseDTO estatisticas;

    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
        estatisticas = new EstatisticaResponseDTO(1l,20.0,20.0,20.0,20.0);
    }

    @Test
    void deveBuscarEstatisticaComSucesso() throws Exception {

        when(estatisticaService.calcularEstatisticaTransacoes(60)).thenReturn(estatisticas);

        mockMvc.perform(get("/estatistica")
                .param("intervaloBusca","60")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(estatisticas.count()));

    }
}
