package tabiMax.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tabiMax.auth.CustomUserDetails;
import tabiMax.dto.CartItemDTO;
import tabiMax.dto.ProductDTO;
import tabiMax.entity.CartItemEntity;
import tabiMax.entity.CartsEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.repository.ICartItemRepository;
import tabiMax.repository.ICartsRepository;
import tabiMax.repository.IProductRepository;
import tabiMax.repository.IUserRepository;
import tabiMax.service.ICartService;

@Service
public class CartService implements ICartService {
	@Autowired
	private ICartItemRepository cartRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private ICartsRepository cartsRepository;

	@Override
	public CartItemEntity save(CartItemDTO cart) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Long userId = userDetails.getUserId();
		CartsEntity carts = cartsRepository.findByUserId(userId);
		System.out.println(carts);
		CartsEntity cartCurrent = null;
		if (carts == null) {
			CartsEntity newCart = new CartsEntity();
			newCart.setUser(userRepository.findById(userId));
			cartCurrent = cartsRepository.save(newCart);
		} else {
			cartCurrent = carts;
		}
		System.out.println(cartCurrent);

		System.out.println(cart);
		CartItemEntity cartEntity = modelMapper.toMapper().map(cart, CartItemEntity.class);

		System.out.println(cart.getProductId());
		cartEntity.setProduct(productRepository.findById(cart.getProductId()));
		cartEntity.setCart(cartCurrent);
		
		return cartRepository.save(cartEntity);
	}

	@Override
	public CartsEntity getCartByUserId(Long userId) {
		CartsEntity cart = cartsRepository.findByUserId(userId);
		return cart != null ? cart : new CartsEntity();
	}

	@Override
	public void deleteCart(Long id) {
		CartItemEntity cart = cartRepository.findById(id);
		cartRepository.delete(cart);

	}

	@Override
	public CartsEntity getCartById(Long id) {

		return cartsRepository.findById(id);
	}

	@Override
	public List<CartsEntity> getAllCarts() {

		return cartsRepository.findAll();
	}

	@Override
	public List<CartItemDTO> findByIds(List<Long> ids) {
		List<CartItemEntity> listCart = new ArrayList<>();
		for (Long id : ids) {
			listCart.add(cartRepository.findById(id));
		}
		List<CartItemDTO> response = listCart.stream()
				.map(cart -> { 
					 CartItemDTO cartDTO =  new CartItemDTO(cart.getTotalPrice(), cart.getSize(), cart.getQuanlity());
					 cartDTO.setProduct(modelMapper.toMapper().map(cart.getProduct(), ProductDTO.class));
					 return cartDTO;
				}).collect(Collectors.toList());
		return response;
	}

}
