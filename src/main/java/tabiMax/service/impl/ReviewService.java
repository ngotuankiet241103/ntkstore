package tabiMax.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tabiMax.dto.ReviewDTO;
import tabiMax.entity.ReviewsEntity;
import tabiMax.entity.UserEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.repository.IProductRepository;
import tabiMax.repository.IReviewRepository;
import tabiMax.repository.IUserRepository;
import tabiMax.service.IReviewService;

@Service
public class ReviewService implements IReviewService {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private IReviewRepository reviewRepository;

	@Async
	@Transactional
	@Override
	public void saveComment(ReviewDTO review) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
		ReviewsEntity reviewEntity = modelMapper.toMapper().map(review, ReviewsEntity.class);
		reviewEntity.setUser(user);
		reviewEntity.setProduct(productRepository.findById(review.getProductId()));
		reviewRepository.save(reviewEntity);

	}

	@Override
	public List<ReviewDTO> findByProductId(Long id) {

		return reviewRepository.findByProductId(id).stream().map(review -> {
			ReviewDTO reviewDTO = modelMapper.toMapper().map(review, ReviewDTO.class);
			reviewDTO.setEmail(review.getUser().getFullName());
			return reviewDTO;
		}).collect(Collectors.toList());
	}

}
