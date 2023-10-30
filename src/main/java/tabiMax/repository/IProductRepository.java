package tabiMax.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import tabiMax.entity.CategoryCommonEntity;
import tabiMax.entity.ProductEntity;

public interface IProductRepository extends Repository<ProductEntity, Long> {
	ProductEntity save(ProductEntity product);

	Page<ProductEntity> findAll(Pageable pageable);

	Optional<ProductEntity> findByName(String productName);

	Page<ProductEntity> findByStatus(int status, Pageable pageable);

	Page<ProductEntity> findByCategoryId(Long id, Pageable pageable);

	Page<ProductEntity> findByCategoryIdAndStatus(Long id, int status, Pageable pageable);

	ProductEntity findById(Long productId);

	@Modifying
	@Query(value = "UPDATE sizeQuantityMap SET sizeQuantityMap = :sizeQuantityMap WHERE size = :size AND productEntity_id = :productEntity_id", nativeQuery = true)
	void updateSizeQuantity(@Param("size") String size, @Param("sizeQuantityMap") Integer sizeQuantityMap,
			@Param("productEntity_id") Long productEntity_id);

	@Modifying
	@Query(value = "UPDATE ProductEntity p SET p.status = ?1  WHERE id =?2")
	void updateStatus(int i, Long id);

	Page<ProductEntity> findByCategoryName(String categoryName, Pageable pageable);

	@Transactional
	@Query("SELECT c FROM  ProductEntity c WHERE c.category.name = ?1 OR c.categoryDetails.name = ?1 "
			)
	Page<ProductEntity> findByCategoryDetailsName(String categoryName, Pageable pageable);

	Optional<ProductEntity> findByCode(String productCode);

}
