package in.reethu.controller;

import in.reethu.dto.Student;
import in.reethu.factory.StudentServiceFactory;
import in.reethu.service.IStudentService;

public class StudentControllerImpl implements IStudentController {
	IStudentService studentService;

	@Override
	public String save(Student student) {
		studentService = StudentServiceFactory.getStudentService();
		return studentService.save(student);
	}

	@Override
	public Student findById(Integer sid) {
		studentService = StudentServiceFactory.getStudentService();
		return studentService.findById(sid);
	}

	@Override
	public String deleteById(Integer sid) {
		studentService = StudentServiceFactory.getStudentService();
		return studentService.deleteById(sid);
	}

	@Override
	public String updateById(Student student) {
		studentService = StudentServiceFactory.getStudentService();
		return studentService.updateById(student);
	}

}
