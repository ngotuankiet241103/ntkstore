package tabiMax.controller.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tabiMax.entity.CartItemEntity;
import tabiMax.entity.CartsEntity;
import tabiMax.service.ICartService;

@Controller
public class CartController {
	@Autowired
	private ICartService cartService;
	@GetMapping("/api/carts/user/{userId}")
	public ResponseEntity<CartsEntity> getCartByUserId(@PathVariable("userId") Long id ){
		return ResponseEntity.ok(cartService.getCartByUserId(id));
	}
	@GetMapping("/api/carts/user/")
	public ResponseEntity<CartsEntity> getCart( ){
		return ResponseEntity.ok(cartService.getCartByUserId(1L));
	}
	@GetMapping("/api/carts/{userId}")
	public ResponseEntity<CartsEntity> getCartById(@PathVariable("userId") Long id  ){
		return ResponseEntity.ok(cartService.getCartById(id));
	}
	@GetMapping("/api/carts")
	public ResponseEntity<List<CartsEntity>> getAllCarts(){
		return ResponseEntity.ok(cartService.getAllCarts());
	}
	@DeleteMapping("/api/carts/{cartId}")
	public void deleteCart(@PathVariable("cartId") Long id) {
		cartService.deleteCart(id);
	}
}
