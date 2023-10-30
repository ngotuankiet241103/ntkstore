package tabiMax.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import tabiMax.entity.CartsEntity;

public interface ICartsRepository extends Repository<CartsEntity, Long>{
	CartsEntity findByUserId(Long id);
	CartsEntity save(CartsEntity newCart);
	CartsEntity findById(Long id);
	List<CartsEntity> findAll();
	
}
