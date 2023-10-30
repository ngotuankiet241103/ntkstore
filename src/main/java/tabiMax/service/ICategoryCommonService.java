package tabiMax.service;

import java.util.List;

import tabiMax.dto.CategoryCommonDTO;

public interface ICategoryCommonService {
	List<CategoryCommonDTO> findAll();

	void save(CategoryCommonDTO category);

	
}
