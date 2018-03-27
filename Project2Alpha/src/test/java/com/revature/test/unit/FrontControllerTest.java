package com.revature.test.unit;

import static org.junit.Assert.assertEquals;

<<<<<<< HEAD
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
=======
>>>>>>> 2e0f05c653b68e75fc58dcc9babcc69f80cf2794
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
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
<<<<<<< HEAD
import com.revature.projecttwo.container.beans.Gender;
import com.revature.projecttwo.container.beans.Post;
=======
>>>>>>> 2e0f05c653b68e75fc58dcc9babcc69f80cf2794
import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.service.CommentService;
import com.revature.projecttwo.container.service.NotificationService;
import com.revature.projecttwo.container.service.PasswordService;
import com.revature.projecttwo.container.service.PostService;
import com.revature.projecttwo.container.service.UserService;
import com.revature.projecttwo.email.EmailServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FrontController.class, secure = false)
public class FrontControllerTest {

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
<<<<<<< HEAD
	public void testRegister() throws Exception
	{
		//mock beans for Testing
		Gender mockGender = new Gender("test");
		Date dobTest = new Date();
		
		//mock User Bean
		Resident mockUser= new Resident(1, "test@test.com", "Testy", "McTesterson", "test", mockGender,
				"555-555-5555", dobTest , "www.testy.com");
		
		//mock JSON
		String tstUsrJSON = "{\"email\":\"test@test.com\",\"password\":\"test\"}";

		
		Mockito.when( userService.registerNewUserAccount(
				Mockito.any(Resident.class))).thenReturn(mockUser);


		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/register")
				.accept(MediaType.APPLICATION_JSON).content(tstUsrJSON)
				.contentType(MediaType.APPLICATION_JSON);
=======
	public void testRegister() throws Exception {
		String mockGender = "test";
		Date dobTest = new Date();

		Resident mockUser = new Resident(1, "test@test.com", "Testy", "McTesterson", "test", mockGender, "555-555-5555",
				dobTest, "www.testy.com");

		String tstUsrJSON = "{\"email\":\"test@test.com\",\"password\":\"test\"}";

		// studentService.addCourse to respond back with mockCourse
		// Mockito.when(userService.registerNewUserAccount(Mockito.any(Resident.class))).thenReturn(mockUser);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").accept(MediaType.APPLICATION_JSON)
				.content(tstUsrJSON).contentType(MediaType.APPLICATION_JSON);
>>>>>>> 2e0f05c653b68e75fc58dcc9babcc69f80cf2794

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
<<<<<<< HEAD
		
		/*// response header location is Null. Not sure why
		assertEquals("http://localhost/register",
				response.getHeader(HttpHeaders.LOCATION));
		*/
	}
	
//	@Test
//	public void testLogin() throws Exception
//	{
//		//mock beans for Testing
//		Gender mockGender = new Gender("test");
//		Date dobTest = new Date();
//		
//		//mock User Bean
//		Resident mockUser= new Resident(1, "test@test.com", "Testy", "McTesterson", "test", mockGender,
//				"555-555-5555", dobTest , "www.testy.com");
//		
//		//mock JSON
//		String tstUsrJSON = "{\"email\":\"test@test.com\",\"password\":\"test\"}";
//
//		
//		Mockito.when( fntContTest.login(
//				Mockito.any(Resident.class))).thenReturn(mockUser);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/login")
//				.accept(MediaType.APPLICATION_JSON).content(tstUsrJSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		
//		/*// response header location is Null. Not sure why
//		assertEquals("http://localhost/register",
//				response.getHeader(HttpHeaders.LOCATION));
//		*/
//	}
//	
//	@Test
//	public void testResetPassword() throws Exception
//	{
//		//mock beans for Testing
//		Gender mockGender = new Gender("test");
//		Date dobTest = new Date();
//		
//		//mock User Bean
//		Resident mockUser= new Resident(1, "test@test.com", "Testy", "McTesterson", "test", mockGender,
//				"555-555-5555", dobTest , "www.testy.com");
//		
//		//mock JSON
//		String tstUsrJSON = "{\"email\":\"test@test.com\"}";
//	
//		
//		Mockito.when( fntContTest.resetPassword(
//				Mockito.anyString())).thenReturn(mockUser);
//	
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/reset")
//				.accept(MediaType.APPLICATION_JSON).content(tstUsrJSON)
//				.contentType(MediaType.APPLICATION_JSON);
//	
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//	
//		MockHttpServletResponse response = result.getResponse();
//	
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//	
//		
//		/*// response header location is Null. Not sure why
//		assertEquals("http://localhost/register",
//				response.getHeader(HttpHeaders.LOCATION));
//		*/
//	}
//	
//	@Test
//	public void testGetProfile()
//	{
//		
//	}
//	
//	@Test
//	public void getUser()
//	{
//		
//	}
//
//	@Test
//	public void updateProfile() throws Exception
//	{
//		//mock beans for Testing
//		Gender mockGender = new Gender("test");
//		Date dobTest = new Date();
//		
////		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
////		ObjectMapper myObjectMapper = new ObjectMapper();
////		myObjectMapper.setDateFormat(df);		
////		String dobStr = df.format(dobTest);
//		
//		//mock User Bean
//		Resident mockUser= new Resident(1, "test@test.com", "Testy", "McTesterson", "test", mockGender,
//				"555-555-5555", dobTest , "www.testy.com");
//		
//		//TODO Add Date Component
//		String tstUsrJSON = "{\"email\":\"test@test.com\",\"firstName\":\"Testy\",\"lastName\":\"McTesterson\",\"genderID\":\"test\",\"phoneNum\":\"555-555-5555\"}";
//		
//		Mockito.when( userService.registerNewUserAccount(
//				Mockito.any(Resident.class))).thenReturn(mockUser);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/users")
//				.accept(MediaType.APPLICATION_JSON).content(tstUsrJSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		
//		/*// response header location is Null. Not sure why
//		assertEquals("http://localhost/register",
//				response.getHeader(HttpHeaders.LOCATION));
//		*/
//	}
//
//	@Test
//	public void testUpdateProfilePic() throws Exception
//	{
//		//mock beans for Testing
//		Gender mockGender = new Gender("test");
//		Date dobTest = new Date();
//		
//		//mock User Bean
//		Post mockPost = new Post();
//		
//		//TODO Add Date Component
//		String tstPstJSON = "{\"url\":\"www.testy.com\"}";
//		
//		Mockito.when( userService.registerNewUserAccount(
//				Mockito.any(Resident.class))).thenReturn(mockPost);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/profilePictures")
//				.accept(MediaType.APPLICATION_JSON).content(tstUsrJSON)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		
//		/*// response header location is Null. Not sure why
//		assertEquals("http://localhost/register",
//				response.getHeader(HttpHeaders.LOCATION));
//		*/
//	}
=======

		System.out.println("Response Location: " + response.getHeader(HttpHeaders.LOCATION));

		/*
		 * // response header location is Null. Not sure why
		 * assertEquals("http://localhost/register",
		 * response.getHeader(HttpHeaders.LOCATION));
		 */
	}

	// @Test
	// public void testLogin()
	// {
	//
	// }
	//
	// @Test
	// public void testResetPassword()
	// {
	//
	// }
	//
	// @Test
	// public void testGetProfile()
	// {
	//
	// }
	//
	// @Test
	// public void getUser()
	// {
	//
	// }
	//
	// @Test
	// public void updateProfile()
	// {
	//
	// }
	//
	// @Test
	// public void testUpdateProfilePic()
	// {
	//
	// }
	//
	// @Test
	// public void testSavePost()
	// {
	//
	// }
	//
	// @Test
	// public void testGetFeed()
	// {
	//
	// }
	//
	// @Test
	// public void testLikePost()
	// {
	//
	// }
	//
	// @Test
	// public void testComment()
	// {
	//
	// }
	//
	// @Test
	// public void testGetComments()
	// {
	//
	// }
	//
	// @Test
	// public void testGetNotification()
	// {
	//
	// }
	//
	// @Test
	// public void testAddNotification()
	// {
	//
	// }
>>>>>>> 2e0f05c653b68e75fc58dcc9babcc69f80cf2794

	@Test
	public void testSavePost() throws Exception
	{
		//mock beans for Testing
		Gender mockGender = new Gender("test");
		Date dobTest = new Date();
		
		//mock User Bean
		Resident mockUser= new Resident(1, "test@test.com", "Testy", "McTesterson", "test", mockGender,
				"555-555-5555", dobTest , "www.testy.com");
		
		//TODO Add Date Component
		String tstUsrJSON = "{\"url\":\"www.testy.com\"}";
		
		Mockito.when( userService.registerNewUserAccount(
				Mockito.any(Resident.class))).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/posts")
				.accept(MediaType.APPLICATION_JSON).content(tstUsrJSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		/*// response header location is Null. Not sure why
		assertEquals("http://localhost/register",
				response.getHeader(HttpHeaders.LOCATION));
		*/
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
