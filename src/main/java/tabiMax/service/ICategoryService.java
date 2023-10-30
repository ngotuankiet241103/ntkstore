package tabiMax.service;

import java.util.List;

import tabiMax.dto.CategoryDTO;
import tabiMax.entity.CategoryEntity;

public interface ICategoryService {
	List<CategoryDTO> findAll();

	CategoryEntity findById(Long categoryId);

	void save(CategoryDTO category);

	List<CategoryDTO> findByCategoryId(Long categoryId);

	CategoryDTO findByName(String categoryName);
}
