package tabiMax.modelMapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import tabiMax.dto.ProductDTO;

public class entityToDto {
	
	public static <TDto, TEntity> List<TDto> entityToDto(Page<TEntity> page, Class<TDto> dtoClass) {
	    List<TDto> listDto = new ArrayList<>();
	    for (TEntity o : page.getContent()) {
	        TDto dto = modelMapper.toMapper().map(o, dtoClass);
	        listDto.add(dto);
	    }
	    return listDto;
	}
}
