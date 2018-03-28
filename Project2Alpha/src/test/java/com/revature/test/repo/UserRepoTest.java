package com.revature.test.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.repo.UserRepo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
@AutoConfigureTestDatabase(replace = NONE)
public class UserRepoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepo usrRep;

	@Test
	public void repoLoads() {
		assertNotNull(usrRep);
	}	
	
	@Test
	public void whenGetByEmail() {
		// given
		Resident usr = new Resident();
		usr.setEmail("test1@testing.com");
		entityManager.persist(usr);
		entityManager.flush();

		// when
		Resident testUser = usrRep.getByEmail(usr.getEmail());

		// then
		assertThat(testUser.getEmail()).isEqualTo(usr.getEmail());
	}

	@Test
	public void whenGetByEmailAndPassword() {
		// given
		Resident usr = new Resident();
		usr.setEmail("test2@test.com");
		usr.setPassword("test2");
		entityManager.persist(usr);
		entityManager.flush();

		// when
		Resident testUser = usrRep.getByEmailAndPassword(usr.getEmail(), usr.getPassword());

		// then
		assertThat(testUser).isEqualTo(usr);
	}
	
	@Test
	public void whenGetById()
	{
		// given
		Resident usr = new Resident();
		usr.setId(1);
		entityManager.persist(usr);
		entityManager.flush();

		// when
		Resident testUser = usrRep.getById(usr.getId());

		// then
		assertThat(testUser).isEqualTo(usr);
	}
	
	@Test
	public void whenFindByFirstNameAndLastNameIgnoreCase()
	{
		// given
		Resident usr = new Resident();
		usr.setFirstName("Testy");
		usr.setLastName("McTesterson");
		entityManager.persist(usr);
		entityManager.flush();

		// when
		Resident testUser = (Resident) usrRep.findByFirstNameAndLastNameIgnoreCase(usr.getFirstName(), usr.getLastName());

		// then
		assertThat(testUser).isEqualTo(usr);
	}

	@Test
	public void findByFirstNameLikeOrLastNameLikeIgnoreCase()
	{
		
	}

}
