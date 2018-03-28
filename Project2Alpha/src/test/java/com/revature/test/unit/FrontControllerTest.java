package com.revature.test.unit;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.projecttwo.container.FrontController;
import com.revature.projecttwo.container.beans.Post;
import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.service.CommentService;
import com.revature.projecttwo.container.service.NotificationService;
import com.revature.projecttwo.container.service.PasswordService;
import com.revature.projecttwo.container.service.PostService;
import com.revature.projecttwo.container.service.UserService;
import com.revature.projecttwo.email.EmailServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FrontController.class, secure = false)
public class FrontControllerTest
{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FrontController fntContTest;

	@MockBean
	private UserService userService;
	@MockBean
	private PostService postService;
	@MockBean
	private CommentService commentService;
	@MockBean
	private NotificationService notificationService;
	@MockBean
	private EmailServiceImpl emailService;
	@MockBean
	private PasswordService passwordService;

	@Test
	public void testRegister() throws Exception
	{
		// mock beans for Testing
		Date dobTest = new Date();

		// mock User Bean
		Resident mockUser = new Resident(1, "test@test.com", "Testy", "McTesterson", "test", "Neutral", "555-555-5555",
				dobTest, "www.testy.com");

		// mock JSON
		String tstUsrJSON = "{\"email\":\"test@test.com\",\"password\":\"test\"}";

		Mockito.when(userService.registerNewUserAccount(Mockito.any(Resident.class))).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").accept(MediaType.APPLICATION_JSON)
				.content(tstUsrJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testLogin() throws Exception
	{
		// mock beans for Testing
		Date dobTest = new Date();

		// mock User Bean
		Resident mockUser = new Resident(1, "test@test.com", "Testy", "McTesterson", "test", "Neutral", "555-555-5555",
				dobTest, "www.testy.com");

		// mock JSON
		String tstUsrJSON = "{\"email\":\"test@test.com\",\"password\":\"test\"}";

		//Mockito.when(fntContTest.login(Mockito.any(Resident.class))).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login").accept(MediaType.APPLICATION_JSON)
				.content(tstUsrJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		/// response header location is Null. Not sure why
		assertEquals("http://localhost/register", response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	public void testResetPassword() throws Exception
	{
		// mock beans for Testing
		Date dobTest = new Date();

		// mock User Bean
		Resident mockUser = new Resident(1, "test@test.com", "Testy", "McTesterson", "test", "Neutral", "555-555-5555",
				dobTest, "www.testy.com");

		// mock JSON
		String tstUsrJSON = "{\"email\":\"test@test.com\"}";

		//Mockito.when(fntContTest.resetPassword(Mockito.any(Resident.class))).thenReturn();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reset").accept(MediaType.APPLICATION_JSON)
				.content(tstUsrJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		// response header location is Null. Not sure why
		assertEquals("http://localhost/register", response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	public void testGetProfile()
	{

	}

	@Test
	public void getUser()
	{

	}

	@Test
	public void updateProfile() throws Exception
	{
		// mock beans for Testing
		Date dobTest = new Date();

		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		// ObjectMapper myObjectMapper = new ObjectMapper();
		// myObjectMapper.setDateFormat(df);
		// String dobStr = df.format(dobTest);

		// mock User Bean
		Resident mockUser = new Resident(1, "test@test.com", "Testy", "McTesterson", "test", "Neutral", "555-555-5555",
				dobTest, "www.testy.com");

		// TODO Add Date Component
		String tstUsrJSON = "{\"email\":\"test@test.com\",\"firstName\":\"Testy\",\"lastName\":\"McTesterson\",\"genderID\":\"test\",\"phoneNum\":\"555-555-5555\"}";

		//Mockito.when(userService.registerNewUserAccount(Mockito.any(Resident.class))).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
				.content(tstUsrJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testUpdateProfilePic() throws Exception
	{
		// mock beans for Testing
		Date dobTest = new Date();

		// mock User Bean
		Post mockPost = new Post();

		// TODO Add Date Component
		String tstPstJSON = "{\"url\":\"www.testy.com\"}";

		//Mockito.when(userService.registerNewUserAccount(Mockito.any(Resident.class))).thenReturn(mockPost);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profilePictures")
				.accept(MediaType.APPLICATION_JSON).content(tstPstJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	

	@Test
	public void testSavePost() throws Exception
	{
		// mock beans for Testing
		Date dobTest = new Date();

		// mock User Bean
		Resident mockUser = new Resident(1, "test@test.com", "Testy", "McTesterson", "test", "Neutral", "555-555-5555",
				dobTest, "www.testy.com");

		// TODO Add Date Component
		String tstUsrJSON = "{\"url\":\"www.testy.com\"}";

		//Mockito.when(userService.registerNewUserAccount(Mockito.any(Resident.class))).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/posts").accept(MediaType.APPLICATION_JSON)
				.content(tstUsrJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testGetFeed()
	{

	}

	@Test
	public void testLikePost()
	{

	}

	@Test
	public void testComment()
	{

	}

	@Test
	public void testGetComments()
	{

	}

	@Test
	public void testGetNotification()
	{

	}

	@Test
	public void testAddNotification()
	{

	}

}
