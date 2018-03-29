package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Notification;
import com.revature.projecttwo.container.repo.NotificationRepo;

@Service
public class NotificationService {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private NotificationRepo notificationRepo;

	public List<Notification> getAllNotification() {
		logger.info("Getting all Notifications:\n\t");
		List<Notification> notification = new ArrayList<>();
		// method reference add method call
		notificationRepo.findAll().forEach(notification::add);

		return notification;
	}

	public Notification getNotification(Integer id) {
		logger.info("Found Notification in DB:\n\t" + id);
		Optional<Notification> notification = notificationRepo.findById(id);

		return notification.get();
	}

	public void addNotification(Notification notification) {
		logger.info("Saving Notification to DB:\n\t" + notification);
		notificationRepo.save(notification);
	}

	public void updateNotification(Notification notification, String id) {
		logger.info("Updating Notification to DB:\n\t" + notification);
		notificationRepo.save(notification);
	}

	public void deleteNotification(Integer id) {
		logger.info("Deleting Notification to DB:+\n\t" + id);
		notificationRepo.deleteById(id);
	}
}
