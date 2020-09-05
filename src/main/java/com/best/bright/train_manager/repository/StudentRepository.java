package com.best.bright.train_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.best.bright.train_manager.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	public List<Student> findByNameLike(String name);
	
	public List<Student> findByNameStartingWith(String name);
	
	public List<Student> findByNameContaining(String name);
	
	public List<Student> findByAgeNot(int age);
	
	public List<Student> findByAgeOrderByNameDesc(int age);
	
	public List<Student> findByAgeIn(List<Integer> ageList);

}
