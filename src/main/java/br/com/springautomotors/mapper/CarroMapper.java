package br.com.springautomotors.mapper;

import br.com.springautomotors.dto.CarroDto;
import br.com.springautomotors.model.Carro;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CarroMapper {

    // dto -> model
    public static Carro toCarro(CarroDto carroDto){
        return new ModelMapper()
                .map(carroDto, Carro.class);
    }

    // model -> dto
    public static CarroDto toDto(Carro carro){
        ModelMapper mapper = new ModelMapper();
        TypeMap<Carro, CarroDto> properties = mapper.createTypeMap(Carro.class, CarroDto.class);
        return mapper.map(carro, CarroDto.class);
    }

}
