package tabiMax.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import tabiMax.dto.TopicDTO;
import tabiMax.entity.TopicEntity;

public interface ITopicRepository extends Repository<TopicEntity, Long>{
	Optional<TopicEntity> findByName(String name);

	TopicEntity save(TopicEntity topicEntity);

	List<TopicEntity> findAll();
}
