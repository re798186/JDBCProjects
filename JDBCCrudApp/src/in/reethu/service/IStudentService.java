package in.reethu.service;

import in.reethu.dto.Student;

public interface IStudentService {
	String save(Student student);
	Student findById(Integer sid);
	String deleteById(Integer sid);
	String updateById(Student student);
	
}
