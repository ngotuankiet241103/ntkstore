package tabiMax.repository;

import org.springframework.data.repository.Repository;

import tabiMax.entity.RefundOrderEntity;

public interface IRefundOrderRepository extends Repository<RefundOrderEntity,Long>{
	void save(RefundOrderEntity refundOrderEntity);
}
