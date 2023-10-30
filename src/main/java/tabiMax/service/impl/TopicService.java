package tabiMax.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabiMax.dto.TopicDTO;
import tabiMax.modelMapper.modelMapper;
import tabiMax.repository.ITopicRepository;
import tabiMax.service.ITopicService;

@Service
public class TopicService implements ITopicService{
	@Autowired
	private ITopicRepository topicRepository;
	@Override
	public List<TopicDTO> findAll() {
		return topicRepository.findAll().stream().map(topic -> modelMapper.toMapper().map(topic, TopicDTO.class)).collect(Collectors.toList());
	}
	
}
