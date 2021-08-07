package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.entities.CourseOffering;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CourseOfferingServiceTest {

	@Autowired
	CourseOfferingService service;
	
	@Test
	@Order(1)
	public void testEnrollLearner() throws ParseException, JsonProcessingException, JsonMappingException{
		int id = service.getMaxId(); //implicit testing of getMaxId()
		String request = "{\"learnerid\":12,\"tcid\":1,\"startdate\":\"2021-09-01\",\"enddate\":\"2021-09-05\"}";
		ObjectMapper mapper = new ObjectMapper();
		CourseOffering offering = mapper.readValue(request, CourseOffering.class);
		service.enrollLearner(offering);
		CourseOffering result = service.getCourseOffering(service.getMaxId()); //implicit testing of getCourseOffering(int id)
		assertThat(result.getLearnerid()).isEqualTo(12);
		assertThat(result.getTcid()).isEqualTo(1);
		assertThat(result.getCourseofferingid()).isEqualTo(id+1);
	}
	
	@Test
	@Order(2)
	public void testUpdateTestScore() {
		service.updateTestScore(service.getMaxId(), 67);
		CourseOffering result = service.getCourseOffering(service.getMaxId());
		assertThat(result.getLearnerid()).isEqualTo(12);
		assertThat(result.getPercentage()).isEqualTo(67);
		assertThat(result.getFeedback()).isEqualTo(null);
		assertThat(result.getStatus()).isEqualTo("FAIL,FEEDBACK_PENDING"); //implicit testing of updateCourseOfferingStatus(CourseOffering offering)
	}
	
	@Test
	@Order(3)
	public void testViewCourseOfferings() {
		List<CourseOffering> offerings = service.viewCourseOfferings();
		assertThat(offerings).size().isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void testRemoveCourseOffering() {
		int id = service.getMaxId();
		service.removeCourseOffering(id);
		CourseOffering offering = service.getCourseOffering(service.getMaxId());
		assertThat(offering.getCourseofferingid()).isEqualTo(id-1);
	}
	
	/*
	 * TEST UPDATE TEST SCORE OF AN INDIVIDUAL
	 */
	@Test
	@Order(5)
	public void updateTestScoreTest(int id, double percentage) {
	}
	
	/*
	 * TEST UPDATE TEST SCORE OF MULTIPLE LEARNERS
	 */
	@Test
	@Order(6)
	public void updateMultipleTestScoresTest(MultipartFile csvFilePath) throws IOException, ParseException {
		
	}

	/*
	 * VIEW COURSE OFFERING
	 */
	@Test
	@Order(7)
	public void viewCourseOfferingsTest(){
	}
	
	/*
	 * GET COURSE OFFERING BY ID
	 */
	@Test
	@Order(8)
	public void getCourseOfferingTest(int id) {
	}
	
	/*
	 * REMOVE COURSE OFFERING
	 */
	@Test
	@Order(9)
	public void removeCourseOfferingTest(int id) {
	}
	
	/*
	 * TEST GENERATES EXCEL SHEET OF SAMPLE DATA FOR ENROLMENT
	 */
	@Test
	@Order(10)
	public void generateExcelForEnrolmentTest(String path) throws IOException {
		
	}

	/*
	 * TEST GENERATES EXCEL SHEET OF SAMPLE DATA FOR TEST SCORE UPDATE 
	 */
	@Test
	@Order(11)
	public void generateExcelForScoreUpdateTest(String path) throws IOException {
		
	}


	@Test
	@Order(5)
	public void testEnrollMultipleLearners() throws IOException, ParseException {
		int id = service.getMaxId();
		Path path = Paths.get("EnrollMultipleLearners.xlsx");
		String name = "EnrollMultipleLearners.xlsx";
		String originalFileName = "EnrollMultipleLearners.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		service.enrollMultipleLearners(file);
		assertThat(service.getMaxId() > id);
	}
	
	@Test
	@Order(6)
	public void testUpdateMultipleTestScores() throws IOException, ParseException {
		Path path = Paths.get("update-score.xlsx");
		String name = "update-score.xlsx";
		String originalFileName = "update-score.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		service.updateMultipleTestScores(file);
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    XSSFRow row = worksheet.getRow(worksheet.getPhysicalNumberOfRows()-1);
		CourseOffering offering = service.getCourseOffering((int)row.getCell(0).getNumericCellValue());
		double percentage = (double)row.getCell(1).getNumericCellValue();
	    assertThat(offering.getPercentage()).isEqualTo(percentage);
	}
	
	@Test
	@Order(7)
	public void testViewTrainerDetails() {
		Map<String, Object> response = service.viewTrainerDetails(1);
		assertThat(response.get("trainerDetails")).hasFieldOrPropertyWithValue("trainerid", 1);
		assertThat(response).hasFieldOrProperty("courses");
		assertThat(response).hasFieldOrProperty("offerings");
	}
	
	@Test
	@Order(8)
	public void testViewCourseDetailsByTrainerId() {
		Map<String, Object> response = service.viewCourseDetailsByTrainerId(1, 2);
		assertThat(response.get("courseDetails")).hasFieldOrPropertyWithValue("courseid", 2);
		assertThat(response).hasFieldOrProperty("avgRating");
		assertThat(response).hasFieldOrProperty("offerings");
	}
	
	@Test
	@Order(9)
	public void testGenerateExcelForEnrolment() throws IOException {
		service.generateExcelForEnrolment("downloads/");
		Path file = Paths.get("downloads/enrollLearners.xlsx");
		assertTrue(Files.exists(file));
	}
	
	@Test
	@Order(10)
	public void testGenerateExcelForScoreUpdate() throws IOException {
		service.generateExcelForScoreUpdate("downloads/");
		Path file = Paths.get("downloads/updateScores.xlsx");
		assertTrue(Files.exists(file));
	}
}
