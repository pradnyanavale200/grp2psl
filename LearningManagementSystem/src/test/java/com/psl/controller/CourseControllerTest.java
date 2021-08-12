package com.psl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.service.CourseService;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	CourseService service;
	
	/*
	 * TEST VIEW ALL COURSES AVAILABLE
	 */
	@Test
	public void getCoursesTest() throws Exception {
		this.mvc.perform(get("/course/allcourses"))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW A COURSE BY COURSEID
	 */
	@Test
	public void getCourseTest() throws Exception {
		this.mvc.perform(get("/course/"+1))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST ADDING A COURSE DETAILS
	 */
	@Test
	public void addCourseTest() throws Exception {
		String request = "{\"coursename\":\"C++\",\"prerequisite\":\"Basic Programming\","
				+ "\"syllabus\":\"OOPs concepts\",\"duration\":\"8 hr\"}";
		this.mvc.perform(post("/course/addcourse")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE COURSE BY ID
	 */
	@Test
	public void updateCourseTest() throws Exception {
		String request = "{\"coursename\":\"C++\",\"prerequisite\":\"Basic Programming\","
				+ "\"syllabus\":\"OOPs concepts\",\"duration\":\"12 hr\"}";
		this.mvc.perform(put("/course/update")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST DELETE COURSE BY ID
	 */
	@Test
	public void removeCourseTest() throws Exception {
		this.mvc.perform(delete("/course/delete/"+1))
		.andExpect(status().isOk());
	}
}
