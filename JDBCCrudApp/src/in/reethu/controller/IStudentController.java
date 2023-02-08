package in.reethu.controller;

import in.reethu.dto.Student;

public interface IStudentController {

	String save(Student student);
	Student findById(Integer sid);
	String deleteById(Integer sid);
	String updateById(Student student);
	
}
