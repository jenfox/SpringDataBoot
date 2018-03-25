package com.revature.projecttwo.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.controller.beans.Notification;
import com.revature.projecttwo.controller.repo.NotificationRepo;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepo notificationRepo;

	public List<Notification> getAllNotification() {
		System.out.println("Getting all Notifications:\n\t");
		List<Notification> notification = new ArrayList<>();
		// method reference add method call
		notificationRepo.findAll().forEach(notification::add);

		return notification;
	}

	public Notification getNotification(Integer id) {
		System.out.println("Found Notification in DB:\n\t" + id);
		Optional<Notification> notification = notificationRepo.findById(id);

		return notification.get();
	}

	public void addNotification(Notification notification) {
		System.out.println("Saving Notification to DB:\n\t" + notification);
		notificationRepo.save(notification);
	}

	public void updateNotification(Notification notification, String id) {
		System.out.println("Updating Notification to DB:\n\t" + notification);
		notificationRepo.save(notification);
	}

	public void deleteNotification(Integer id) {
		System.out.println("Deleting Notification to DB:+\n\t" + id);
		notificationRepo.deleteById(id);
	}
}
