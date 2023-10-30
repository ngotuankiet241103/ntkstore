package tabiMax.repository;

import org.springframework.data.repository.Repository;

import tabiMax.entity.CartItemEntity;

public interface ICartItemRepository extends Repository<CartItemEntity, Long>{
	CartItemEntity save(CartItemEntity cart);
	CartItemEntity findById(Long id);
	void delete(CartItemEntity cart);
}
