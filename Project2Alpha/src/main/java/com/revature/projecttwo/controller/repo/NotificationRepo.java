package com.revature.projecttwo.controller.repo;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.controller.beans.Notification;

public interface NotificationRepo extends CrudRepository<Notification, Integer> {

}
