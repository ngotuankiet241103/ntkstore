package tabiMax.service;

import java.util.List;

import tabiMax.dto.CartItemDTO;
import tabiMax.entity.CartItemEntity;
import tabiMax.entity.CartsEntity;

public interface ICartService {
	CartItemEntity save(CartItemDTO cart);

	CartsEntity getCartByUserId(Long userId);

	void deleteCart(Long id);

	CartsEntity getCartById(Long id);

	List<CartsEntity> getAllCarts();

	List<CartItemDTO> findByIds(List<Long> ids);
}
