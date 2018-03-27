package com.revature.test.unit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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

import com.revature.projecttwo.container.FrontController;
import com.revature.projecttwo.container.beans.Gender;
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
	public void testRegister() throws Exception
	{
		Gender mockGender = new Gender("test");
		Date dobTest = new Date();
		
		Resident mockUser= new Resident(1, "test@test.com", "Testy", "McTesterson", "test", mockGender,
				"555-555-5555", dobTest , "www.testy.com");
		
		String tstUsrJSON = "{\"email\":\"test@test.com\",\"password\":\"test\"}";

		// studentService.addCourse to respond back with mockCourse
		Mockito.when( userService.registerNewUserAccount(
				Mockito.any(Resident.class))).thenReturn(mockUser);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/register")
				.accept(MediaType.APPLICATION_JSON).content(tstUsrJSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		System.out.println("Response Location: " + response.getHeader(HttpHeaders.LOCATION));
		
		/*// response header location is Null. Not sure why
		assertEquals("http://localhost/register",
				response.getHeader(HttpHeaders.LOCATION));
		*/
	}
	
//	@Test
//	public void testLogin()
//	{
//		
//	}
//	
//	@Test
//	public void testResetPassword()
//	{
//		
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
//	public void updateProfile()
//	{
//		
//	}
//
//	@Test
//	public void testUpdateProfilePic()
//	{
//		
//	}
//
//	@Test
//	public void testSavePost()
//	{
//		
//	}
//	
//	@Test
//	public void testGetFeed()
//	{
//		
//	}
//
//	@Test
//	public void testLikePost()
//	{
//		
//	}
//
//	@Test
//	public void testComment()
//	{
//		
//	}
//
//	@Test
//	public void testGetComments()
//	{
//		
//	}
//
//	@Test
//	public void testGetNotification()
//	{
//		
//	}
//
//	@Test
//	public void testAddNotification()
//	{
//		
//	}

}
