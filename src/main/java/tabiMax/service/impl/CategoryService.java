package tabiMax.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabiMax.dto.CategoryCommonDTO;
import tabiMax.dto.CategoryDTO;
import tabiMax.entity.CategoryEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.repository.ICategoryCommonRepository;
import tabiMax.repository.ICategoryRepository;
import tabiMax.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private ICategoryRepository categoryRepository;
	@Autowired
	private ICategoryCommonRepository categoryCommonRepository;

	@Override
	public List<CategoryDTO> findAll() {

		return categoryRepository.findAll().stream().map(category -> {
			CategoryDTO categoryDTO = modelMapper.toMapper().map(category, CategoryDTO.class);
			CategoryCommonDTO categoryCommon = modelMapper.toMapper().map(category.getCategory(),
					CategoryCommonDTO.class);
			categoryDTO.setCategory(categoryCommon);
			return categoryDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public CategoryEntity findById(Long categoryId) {

		return categoryRepository.findById(categoryId);
	}

	@Override
	public void save(CategoryDTO category) {
		CategoryEntity categoryEntity = modelMapper.toMapper().map(category, CategoryEntity.class);
		categoryEntity.setCategory(categoryCommonRepository.findById(category.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category is not found")));
		categoryRepository.save(categoryEntity);

	}

	@Override
	public List<CategoryDTO> findByCategoryId(Long categoryId) {

		return categoryRepository.findByCategoryId(categoryId).stream()
				.map(category -> modelMapper.toMapper().map(category, CategoryDTO.class)).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO findByName(String categoryName) {
		
		return categoryRepository.findByName(categoryName).map(category -> modelMapper.toMapper().map(category, CategoryDTO.class)).orElse(null);
	}

}
