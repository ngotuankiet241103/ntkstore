package tabiMax.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import tabiMax.dto.BlogDTO;
import tabiMax.entity.BlogEntity;

public interface IBlogRepository extends Repository<BlogEntity, Long>{
	void save(BlogEntity blogEntity);

	Page<BlogEntity> findAll(Pageable pageable);

	BlogEntity findById(Long blogId);

	Optional<BlogEntity> findByCode(String code);

	Page<BlogEntity> findByTopicCode(String topicCode, Pageable pageable);

	Page<BlogEntity> findByTagsCode(String tagCode, Pageable pageable);
}
