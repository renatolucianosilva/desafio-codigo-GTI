package desafio_codigo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import desafio_codigo.api.request.AgendamentoVisitaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc

class AgendamentoVisitaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void agendarNovaVisita_deveRetornar201_quandoVisitaAgendadaComSucesso() throws Exception {
        // Preparação dos dados de teste
        String nomeVisitante = "Maria das Graças";
        String nomeCustodiado = "Ruan Pablo";

        AgendamentoVisitaRequest agendamento = new AgendamentoVisitaRequest();
        agendamento.setVisitanteCpf("123.456.789-00");
        agendamento.setNumeroProntuario("123456");
        agendamento.setDataAgendamento("10/04/2025");
        agendamento.setHoraAgendamento("10:00");

        // Execução do teste
        mockMvc.perform(MockMvcRequestBuilders.post("/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agendamento))
                        .param("nomeVisitante", nomeVisitante)
                        .param("nomeCustodiado", nomeCustodiado))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        //.andExpect(MockMvcResultMatchers.jsonPath("$.algumCampo").value("valorEsperado")); // Adicione verificações de JSON

    }

}