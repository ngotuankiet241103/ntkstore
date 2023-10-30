package tabiMax.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tabiMax.dto.ProductDTO;
import tabiMax.entity.ProductEntity;

public interface IProductService {
	void save(ProductDTO product);
	Map<String, Object> findAll(Pageable pageable);
	ProductDTO findByName(String productnName);
	Page<ProductEntity> findByCategoryOrStatus(ProductDTO product, Pageable pageable);
	ProductEntity findById(Long productId);
	Map<String, Object> findByCategoryName(String categoryName, Pageable pageable);
	Map<String, Object> findByCategoryDetailsName(String categoryName, Pageable pageable);
	List<ProductDTO> findRelateProduct(ProductDTO product);
	List<ProductDTO> findNewProduct(Pageable pageable);
	ProductDTO findByCode(String productCode);
	
}
