package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Learner;

public interface ILearnerDAO extends CrudRepository<Learner,Integer>{
	@Query(value = "select learnerid, name, department, email, phonenumber, password=null as password from learner", nativeQuery = true)
	public List<Learner> findAll();

	@Query(value="select max(learnerid) from learner", nativeQuery=true)
	public Integer getNextId();
	
	
	@Query(value="select l.name, c.courseid, c.coursename from learner l, course c, TeacherCourseMapping tc, courseoffering co where c.courseid = tc.courseid and co.learnerid=l.learnerid and tc.tcid = co.tcid", nativeQuery=true)
	public List courseAttended();
	
	@Query(value="select l.name as name,c.coursename as coursename,co.Percentage as percentage,co.status as status from learner l,courseoffering co,TeacherCourseMapping tc,course c where c.courseid = tc.courseid and co.learnerid=l.learnerid and tc.tcid = co.tcid",nativeQuery=true)
	public List scoreAndStatus();
}
