package in.reethu.dao;

import in.reethu.dto.Student;

public interface IStudentDao {
	String save(Student student);
	Student findById(Integer sid);
	String deleteById(Integer sid);
	String updateById(Student student);
	
}
