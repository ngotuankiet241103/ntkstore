package tabiMax.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import redis.clients.jedis.Jedis;
import tabiMax.config.RedisCache;
import tabiMax.dto.CategoryCommonDTO;
import tabiMax.dto.ProductDTO;
import tabiMax.entity.ProductEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.paging.pageRequest;
import tabiMax.repository.ICategoryCommonRepository;
import tabiMax.repository.ICategoryRepository;
import tabiMax.repository.IProductRepository;
import tabiMax.service.IProductService;
import tabiMax.utils.Functional;
import tabiMax.utils.RedisUtils;
import tabiMax.utils.handleString;

@Service
public class ProductService implements IProductService {
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private ICategoryRepository categoryRepository;
	@Autowired
	private ICategoryCommonRepository categoryCommonRepository;

	@Override
	public void save(ProductDTO product) {

		long total = productRepository.count();
		removeCaching(total);
		String code = handleString.removeDiacritics(product.getName());
		product.setCode(handleString.strToCode(code));
		ProductEntity productEntity = modelMapper.toMapper().map(product, ProductEntity.class);
		productEntity.setCategoryDetails(categoryRepository.findById(product.getCategoryId()));
		productEntity.setCategory(categoryCommonRepository.findById(product.getCategoryCommonId())
				.orElseThrow(() -> new RuntimeException("Category is not found")));
		productRepository.save(productEntity);
	}

	@Async
	private void removeCaching(long total) {
		Jedis jedis = RedisCache.jedisPool.getResource();
		for (int i = 0; i < total; i++) {
			jedis.del("product?page=" + i);
		}

	}

	@Cacheable(cacheNames = "products", key = "products")
	@Override
	public Map<String, Object> findAll(Pageable pageable) {
		Page<ProductEntity> page = null;
		Map<String, Object> response = new HashMap<>();
		Jedis jedis = RedisCache.jedisPool.getResource();
		String key = "product?page=" + pageable.getOffset();
		if (jedis.hgetAll(key.getBytes()).size() > 0) {
			Map<byte[], byte[]> product = jedis.hgetAll(key.getBytes());
			return RedisUtils.getObject(product, Functional.byteToString, Functional.byteToObject);

		} else {
			page = productRepository.findAll(pageable);
			System.out.println(page);
			List<ProductEntity> products = page.getContent();
			System.out.println(page.getNumber());
			System.out.println(page.getTotalPages());
			pageRequest pagination = new pageRequest(page.getNumber(), page.getTotalPages());
			response.put("products", products);
			response.put("pagination", pagination);
			Map<byte[], byte[]> map = new HashMap<>();
			map.put("products".getBytes(), SerializationUtils.serialize(products));
			map.put("pagination".getBytes(), SerializationUtils.serialize(pagination));
			jedis.hset(key.getBytes(), map);

		}

		return response;
	}

	@Override
	public ProductDTO findByName(String productnName) {
		return productRepository.findByName(productnName)
				.map(product -> modelMapper.toMapper().map(product, ProductDTO.class))
				.orElseThrow(() -> new RuntimeException("product is not exits"));
	}

	@Override
	public Page<ProductEntity> findByCategoryOrStatus(ProductDTO product, Pageable pageable) {
		ProductEntity productEntity;
		Page<ProductEntity> listProduct = null;

		if (product.getStatus() != -1 && product.getCategoryId() != -1) {
//				product.setCategory(categoryRepository.findById(product.getCategoryId()));
//				productEntity = modelMapper.toMapper().map(product, ProductEntity.class);
			listProduct = productRepository.findByCategoryIdAndStatus(product.getCategoryId(), product.getStatus(),
					pageable);
			System.out.println(listProduct);
		} else if (product.getStatus() == -1 && product.getCategoryId() != -1) {
			listProduct = productRepository.findByCategoryId(product.getCategoryId(), pageable);
			System.out.println(listProduct);
		} else if (product.getStatus() != -1 && product.getCategoryId() == -1) {
			listProduct = productRepository.findByStatus(product.getStatus(), pageable);
			System.out.println(listProduct);
		} else {
			listProduct = productRepository.findAll(pageable);
		}

		return listProduct;
	}

	@Override
	public ProductEntity findById(Long productId) {

		return productRepository.findById(productId);
	}

	@Override
	public Map<String, Object> findByCategoryName(String categoryName, Pageable pageable) {
		Map<String, Object> response = new HashMap<>();
		Page<ProductEntity> listProduct = productRepository.findByCategoryName(categoryName, pageable);
		response.put("products", listProduct.getContent().stream()
				.map(product -> modelMapper.toMapper().map(product, ProductDTO.class)).collect(Collectors.toList()));
		response.put("pagination", new pageRequest(listProduct.getNumber(), listProduct.getTotalPages()));
		return response;
	}

	@Override
	public Map<String, Object> findByCategoryDetailsName(String categoryName, Pageable pageable) {
		Map<String, Object> response = new HashMap<>();

		Page<ProductEntity> listProduct = productRepository.findByCategoryDetailsName(categoryName, pageable);
		response.put("products", listProduct.getContent().stream().map(product -> {
			ProductDTO productDTO = modelMapper.toMapper().map(product, ProductDTO.class);
			productDTO.getCategoryDetails()
					.setCategory(modelMapper.toMapper().map(product.getCategory(), CategoryCommonDTO.class));
			return productDTO;
		}).collect(Collectors.toList()));
		response.put("pagination", new pageRequest(listProduct.getNumber(), listProduct.getTotalPages()));
		return response;
	}

	@Override
	public List<ProductDTO> findRelateProduct(ProductDTO product) {
		List<ProductDTO> listProduct = productRepository
				.findByCategoryDetailsName(product.getCategoryDetails().getName(), null).getContent().stream()
				.map(productEntity -> modelMapper.toMapper().map(productEntity, ProductDTO.class))
				.collect(Collectors.toList());
		for (ProductDTO pr : listProduct) {
			if (pr.getId() == product.getId()) {
				listProduct.remove(pr);
				break;
			}
		}
		EuclideanDistance distanceMeasure = new EuclideanDistance();
		for (ProductDTO p : listProduct) {
			double distace = Math
					.pow(p.getPrice() - (product.getPrice() / listProduct.get(listProduct.size() - 1).getPrice()), 2);
			p.setDistance(distace);
		}
		Collections.sort(listProduct, (o1, o2) -> o1.getDistance() - o2.getDistance() > 0 ? 1 : 0);
		int k = 4;
		if (k > listProduct.size()) {
			k = listProduct.size();
		}
		List<ProductDTO> newProducts = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			newProducts.add(listProduct.get(i));

		}

		return newProducts;
	}

	@Override
	public List<ProductDTO> findNewProduct(Pageable pageable) {
		List<ProductDTO> response = productRepository.findAll(pageable).getContent().stream()
				.map(product -> modelMapper.toMapper().map(product, ProductDTO.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public ProductDTO findByCode(String productCode) {
		return productRepository.findByCode(productCode)
				.map(product -> modelMapper.toMapper().map(product, ProductDTO.class))
				.orElseThrow(() -> new RuntimeException("product is not exits"));
	}

	@Override
	public Map<String, Object> findBySize(String sizes) {
		Map<String, Object> response = new HashMap<>();
		response.put("products", productRepository.findBySize(sizes));
		return response;
	}

	@Override
	public List<ProductDTO> querySearch(String query, Pageable pageable) {
		String param = "%" + query + "%";
		return productRepository.findByParam(param, pageable).getContent().stream()
				.map(product -> modelMapper.toMapper().map(product, ProductDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Map<String, Object> queryProduct(String query, Pageable pageable) {
		Map<String, Object> response = new HashMap<>();
		String param = "%" + query + "%";
		
		Page<ProductEntity> page = productRepository.findByParam(param, pageable);
		List<ProductDTO> poDtos = page.getContent().stream()
				.map(product -> modelMapper.toMapper().map(product, ProductDTO.class)).collect(Collectors.toList());
		pageRequest paging = new pageRequest(page.getNumber(), page.getTotalPages());
		response.put("products",poDtos);
		response.put("pageable", paging);
		return response;
	}

}
