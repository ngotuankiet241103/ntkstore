package tabiMax.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import tabiMax.entity.CategoryEntity;

public interface ICategoryRepository extends Repository<CategoryEntity, Long>{
	List<CategoryEntity> findAll();

	CategoryEntity findById(Long categoryId);

	void save(CategoryEntity categoryEntity);

	List<CategoryEntity> findByCategoryId(Long categoryId);

	Optional<CategoryEntity> findByName(String categoryName);
}
