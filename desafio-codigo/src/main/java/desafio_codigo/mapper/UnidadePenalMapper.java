package desafio_codigo.mapper;
import desafio_codigo.api.request.UnidadePenalRequest;
import desafio_codigo.api.response.UnidadePenalResponse;
import desafio_codigo.modell.UnidadePenal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnidadePenalMapper {

    UnidadePenal toUnidadePenal (UnidadePenalRequest unidadePenalRequest);

    UnidadePenalResponse toUnidadePenalResponse (UnidadePenal unidadePenal);

    List<UnidadePenalResponse> toUnidadePenaResponseList (List<UnidadePenal> unidadePenal);


}
