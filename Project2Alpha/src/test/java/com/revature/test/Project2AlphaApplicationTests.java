package com.revature.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.projecttwo.container.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class Project2AlphaApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	

}
