package desafio_codigo.mapper;

import desafio_codigo.api.request.CustodiadoRequest;
import desafio_codigo.api.response.CustodiadoResponse;
import desafio_codigo.modell.Custodiado;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustodiadoMapper {

   Custodiado toCustodiado (CustodiadoRequest custodiadoRequest);

   CustodiadoResponse toCustodiadoResponse (Custodiado custodiado);


}
