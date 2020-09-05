package com.best.bright.train_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.bright.train_manager.model.Student;
import com.best.bright.train_manager.repository.StudentRepository;

@Controller
public class StuentController {
	@Autowired
	private StudentRepository studentRepositroy;
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestParam String name
	      , @RequestParam String rollNo) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request

	    Student n = new Student();
	    n.setName(name);
	    n.setRollNo(rollNo);
	    studentRepositroy.save(n);
	    return "Saved";
	  }
	@GetMapping("/call")
	public @ResponseBody String test() {
		return "Hello Test";
	}
	@GetMapping("/students")
	public @ResponseBody List<Student> getStudentList(){
		return studentRepositroy.findAll();
	}
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("student", new Student());
		return "create_student";
	}
	@PostMapping("/save_student")
	public String saveStudent(@ModelAttribute("student")Student std,Model model) {
		
		studentRepositroy.save(std);
		
		model.addAttribute("students", studentRepositroy.findAll());
		return "student_list";
	}
	@GetMapping("/ajax_save")
	public String ajax_save() {
		return "ajax_save";
	}
	@PostMapping("/ajax_save")
	public @ResponseBody Student save_student(@RequestBody Student std) {
		Student student=studentRepositroy.save(std);
		return student;
	}
	@DeleteMapping("/delete/{id}")
	public @ResponseBody boolean delete_student(@PathVariable("id")Long id) {
		Student std=studentRepositroy.findById(id).get();
		studentRepositroy.delete(std);
		return true;
	}
	@GetMapping("/find_by_name_like/{name}")
	public @ResponseBody List<Student> findByNameLike(@PathVariable(value = "name")String name){
		return studentRepositroy.findByNameLike(name);
	}
	@GetMapping("/find_by_name_start/{name}")
	public @ResponseBody List<Student> getStudentList(@PathVariable(value = "name")String name){
		return studentRepositroy.findByNameStartingWith(name);
	}
	@GetMapping("/find_by_name_contain/{name}")
	public @ResponseBody List<Student> getStudentListByName(@PathVariable(value = "name")String name){
		return studentRepositroy.findByNameContaining(name);
	}
	@GetMapping("/find_by_age/{age}")
	public @ResponseBody List<Student> getAgeList(@PathVariable(value = "age")int age){
		return studentRepositroy.findByAgeNot(age);
	}

	/*
	 * @GetMapping("/find_by_age/{age}") public @ResponseBody List<Student>
	 * getAgeCollection(@PathVariable(value = "age")int age){ return
	 * studentRepositroy.findByAgeIn(age); }
	 */
	@GetMapping("/find_by_age_orderby_name/{age}")
	public @ResponseBody List<Student> getAgeListByName(@PathVariable(value = "age")int age){
		return studentRepositroy.findByAgeOrderByNameDesc(age);
	}
}
