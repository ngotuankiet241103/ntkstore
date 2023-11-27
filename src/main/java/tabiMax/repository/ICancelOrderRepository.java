package tabiMax.repository;

import org.springframework.data.repository.Repository;

import tabiMax.entity.CancelOrderEntity;

public interface ICancelOrderRepository extends Repository<CancelOrderEntity, Long>{
	void save(CancelOrderEntity cancelOrderEntity);

}
