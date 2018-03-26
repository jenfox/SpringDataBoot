package com.revature.projecttwo.container.repo;

import org.springframework.data.repository.CrudRepository;

import com.revature.projecttwo.container.beans.Notification;

public interface NotificationRepo extends CrudRepository<Notification, Integer> {

}
