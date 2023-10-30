package tabiMax.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import tabiMax.dto.BlogDTO;
import tabiMax.entity.BlogEntity;

public interface IBlogSerivce {

	void save(BlogDTO blog);

	Map<String, Object> findAll(Pageable pageable);

	BlogDTO findByCode(String code);

	Map<String, Object> findByTagCode(String tagCode, Pageable pageable);

	Map<String, Object> findByTopicCode(String topicCode, Pageable pageable);

}
