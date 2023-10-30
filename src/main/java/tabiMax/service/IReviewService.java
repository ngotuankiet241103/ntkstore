package tabiMax.service;

import java.util.List;

import tabiMax.dto.ReviewDTO;

public interface IReviewService {

	void saveComment(ReviewDTO review);

	List<ReviewDTO> findByProductId(Long id);

}
