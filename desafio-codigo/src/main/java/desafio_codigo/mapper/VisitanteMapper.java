package desafio_codigo.mapper;

import desafio_codigo.api.request.VisitanteRequest;
import desafio_codigo.api.response.VisitanteResponse;
import desafio_codigo.modell.Visitante;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VisitanteMapper {

   Visitante toVisitante (VisitanteRequest visitanteRequest);

   List<VisitanteResponse> toVisitanteResponseList (List<Visitante> visitantes);

   VisitanteResponse toVisitanteResponse (Visitante visitante);

}
