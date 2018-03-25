package com.revature.projecttwo.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // seterotype annotation - marks as Spring business service
public class TopicService {

	@Autowired
	private TopicRepo topicRepo;

	// new ArrayList<> is mutable, Arrays.asList is not
	private List<Topic> topics = new ArrayList<>(Arrays.asList(new Topic("spring", "Spring Framework", "stuff"),
			new Topic("spring2", "Spring Framework", "stuff"), new Topic("spring3", "Spring Framework", "stuff")));

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		// method reference add method call
		topicRepo.findAll().forEach(topics::add);

		return topics;
	}

	public Topic getTopic(String id) {
		// stream filtering
		// /return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		Optional<Topic> topic = topicRepo.findById(id);

		return topic.get();
	}

	public void addTopic(Topic topic) {
		topicRepo.save(topic);
	}

	public void updateTopic(Topic topic, String id) {
		// UPDATE ALL THE THINGS.. matching id
		// topics.forEach(t -> {
		// if (id.equals(t.getId())) {
		// t.setDescription(topic.getDescription());
		// t.setId(topic.getDescription());
		// t.setName(topic.getName());
		// }
		// });
		/**
		 * for(int i =0; i< topics.size(); i++) { Topic t = topic.get(i);
		 * if(t.getId().equals(id)) { topics.set(i, topic); return; } }
		 */
		topicRepo.save(topic);
	}

	public void deleteTopic(String id) {
		// topics.removeIf(t -> t.getId().equals(id));
		topicRepo.deleteById(id);
	}
}
