package desafio_codigo.mapper;

import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.api.response.AgendamentoVisitaResponse;
import desafio_codigo.modell.AgendamentoVisita;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AgendamentoVisitaMapper {

   AgendamentoVisita toAgendamentoVisita (AgendamentoVisitaRequest agendamentoVisitaRequest);

   AgendamentoVisitaResponse toAgendamentoVisitaResponse (AgendamentoVisita agendamentoVisita);

   List<AgendamentoVisitaResponse>  toAgedamentoVisitaResponseList (List<AgendamentoVisita> agendamentoVisitaList);
}
