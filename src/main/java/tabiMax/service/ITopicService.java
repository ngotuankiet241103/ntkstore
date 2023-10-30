package tabiMax.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import tabiMax.dto.TopicDTO;

public interface ITopicService {

	List<TopicDTO> findAll();

	

}
