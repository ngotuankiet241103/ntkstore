package tabiMax.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import tabiMax.entity.CategoryCommonEntity;

public interface ICategoryCommonRepository extends Repository<CategoryCommonEntity, Long> {
	List<CategoryCommonEntity> findAll();
	void save(CategoryCommonEntity categoryEntity);
	Optional<CategoryCommonEntity> findById(Long categoryId);
	CategoryCommonEntity findByName(String categoryName);
	
}
