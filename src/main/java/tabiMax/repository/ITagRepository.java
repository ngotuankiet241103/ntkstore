package tabiMax.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import tabiMax.entity.TagEntity;

public interface ITagRepository extends Repository<TagEntity, Long>{
	Optional<TagEntity> findByName(String name);

	TagEntity save(TagEntity tagEntity);
}
