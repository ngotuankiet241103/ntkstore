package tabiMax.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tabiMax.dto.BlogDTO;
import tabiMax.entity.BlogEntity;
import tabiMax.entity.CommentEntity;
import tabiMax.entity.TagEntity;
import tabiMax.entity.TopicEntity;
import tabiMax.modelMapper.modelMapper;
import tabiMax.paging.pageRequest;
import tabiMax.repository.IBlogRepository;
import tabiMax.repository.ICommentRepository;
import tabiMax.repository.ITagRepository;
import tabiMax.repository.ITopicRepository;
import tabiMax.service.IBlogSerivce;
import tabiMax.service.ICommentService;
import tabiMax.utils.handleString;

@Service
public class BlogService implements IBlogSerivce {
	@Autowired
	private IBlogRepository blogRepository;
	@Autowired
	private ITopicRepository topicRepository;
	@Autowired
	private ITagRepository tagRepository;
	@Autowired
	private ICommentRepository commentRepository;

	@Override
	public void save(BlogDTO blog) {
		BlogEntity blogEntity = null;
		String name = blog.getTopicName();
		String code = handleString.removeDiacritics(blog.getTitle());
		code = handleString.strToCode(code);
		TopicEntity topicEntity = topicRepository.findByName(name).orElse(null);
		List<TagEntity> tags = getTags(blog);
		if (topicEntity == null) {
			topicEntity = topicRepository.save(new TopicEntity(name, handleString.strToCode(name)));
		}

		blogEntity = new BlogEntity(blog.getTitle(), blog.getContent(), topicEntity, tags);
		blogEntity.setCode(code);
		blogEntity.setImage(blog.getImage());
		blogEntity.setDescription(blog.getDescription());
		blogRepository.save(blogEntity);

	}

	@Async
	private List<TagEntity> getTags(BlogDTO blog) {

		List<TagEntity> tags = new ArrayList<>();
		String[] tagsName = blog.getTagName().split(",");
		for (String tag : tagsName) {
			TagEntity tagEntity = tagRepository.findByName(tag).orElse(null);
			if (tagEntity == null) {
				tagEntity = tagRepository.save(new TagEntity(tag, tag));
			}
			tags.add(tagEntity);
		}
		return tags;
	}

	@Override
	public Map<String, Object> findAll(Pageable pageable) {
		Map<String, Object> response = new HashMap<>();
		Page<BlogEntity> page = blogRepository.findAll(pageable);
		List<BlogDTO> blogs = page.getContent().stream().map(blogEntity -> {
			BlogDTO blog = modelMapper.toMapper().map(blogEntity, BlogDTO.class);
			blog.setTopicName(blogEntity.getTopic().getName());

			return blog;
		}).collect(Collectors.toList());
		pageRequest paging = new pageRequest(page.getNumber(), page.getTotalPages());
		response.put("paging", paging);
		response.put("blogs", blogs);
		return response;
	}

	@Override
	public BlogDTO findByCode(String code) {
		BlogEntity blog = blogRepository.findByCode(code).orElse(null);
		blog.setComments(commentRepository.findByBlogId(blog.getId()));
		BlogDTO blogDTO = modelMapper.toMapper().map(blog, BlogDTO.class);
//		Map<String, List<CommentEntity>> comment = blog.getComments().stream()
//				.collect(Collectors.groupingBy(cmt -> cmt.getTree_id()));
		blogDTO.setUserName(blog.getCreatedBy());
//		blogDTO.setComment(comment);
		return blogDTO;
	}

	@Override
	public Map<String, Object> findByTagCode(String tagCode, Pageable pageable) {
		Map<String, Object> response = new HashMap<>();
		Page<BlogEntity> page = blogRepository.findByTagsCode(tagCode, pageable);
		List<BlogDTO> blogs = page.getContent().stream().map(blogEntity -> {
			BlogDTO blog = modelMapper.toMapper().map(blogEntity, BlogDTO.class);
			blog.setTopicName(blogEntity.getTopic().getName());

			return blog;
		}).collect(Collectors.toList());
		pageRequest paging = new pageRequest(page.getNumber(), page.getTotalPages());
		response.put("paging", paging);
		response.put("blogs", blogs);
		return response;
	}

	@Override
	public Map<String, Object> findByTopicCode(String topicCode, Pageable pageable) {
		Map<String, Object> response = new HashMap<>();
		Page<BlogEntity> page = blogRepository.findByTopicCode(topicCode, pageable);
		List<BlogDTO> blogs = page.getContent().stream().map(blogEntity -> {
			BlogDTO blog = modelMapper.toMapper().map(blogEntity, BlogDTO.class);
			blog.setTopicName(blogEntity.getTopic().getName());

			return blog;
		}).collect(Collectors.toList());
		pageRequest paging = new pageRequest(page.getNumber(), page.getTotalPages());
		response.put("paging", paging);
		response.put("blogs", blogs);
		return response;
	}
}
