package tabiMax.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import tabiMax.entity.ReviewsEntity;

public interface IReviewRepository extends Repository<ReviewsEntity, Long>{
	void save(ReviewsEntity reviewEntity);

	List<ReviewsEntity> findByProductId(Long id);
}
