package tabiMax.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabiMax.dto.CategoryCommonDTO;
import tabiMax.entity.CategoryCommonEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.repository.ICategoryCommonRepository;
import tabiMax.service.ICategoryCommonService;

@Service
public class CategoryCommonService implements ICategoryCommonService{
	@Autowired
	private ICategoryCommonRepository categoryCommonRepository;

	@Override
	public List<CategoryCommonDTO> findAll() {
		return categoryCommonRepository.findAll()
				.stream()
				.map(category -> modelMapper.toMapper().map(category, CategoryCommonDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void save(CategoryCommonDTO category) {
		CategoryCommonEntity categoryEntity = modelMapper.toMapper().map(category, CategoryCommonEntity.class);
		categoryCommonRepository.save(categoryEntity);
		
	}

	
	
	

}
