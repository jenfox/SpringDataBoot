package com.revature.projecttwo.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@Autowired // declared as dependency
	private TopicService topicService;

	@RequestMapping("/topics")
	// Gets auto converted to JSONs Restcontroller
	public List<Topic> getAllTopics() {
		// topic service is supplying list
		return topicService.getAllTopics();
	}

	// method can access id from URL
	@RequestMapping("/topics/{id}") // variable url, can match names or declare in ("")
	public Topic getTopic(@PathVariable String id) { // match it to id
		return topicService.getTopic(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		// get post body and convert to topic
		// add to topics
		topicService.addTopic(topic);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		// get post body and convert to topic
		// add to topics
		topicService.updateTopic(topic, id);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
	public void deleteTopic(@PathVariable String id) {
		// get post body and convert to topic
		// add to topics
		System.out.println("deleting");
		topicService.deleteTopic(id);

	}

}
