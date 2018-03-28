package com.revature.test.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.projecttwo.container.beans.Post;
import com.revature.projecttwo.container.repo.PostRepo;

import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
@AutoConfigureTestDatabase(replace = NONE)
public class PostRepoTest
{
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PostRepo postRep;

	@Test
	public void repoLoads() {
		assertNotNull(postRep);
	}	
	
	@Test
	public void testFindTop20ByOrderByDateCreatedDesc()
	{
		ArrayList<Post> posts = new ArrayList<Post>();
		Post pst;
		
		for(int i = 0; i < 25; i++)
		{
			pst= new Post();
			entityManager.persist(pst);
			entityManager.flush();
			
			posts.add(pst);
		}
		
	}

}
