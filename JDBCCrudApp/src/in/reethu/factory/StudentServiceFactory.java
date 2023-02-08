package in.reethu.factory;

import in.reethu.service.IStudentService;
import in.reethu.service.StudentServiceImpl;

public class StudentServiceFactory {
	private StudentServiceFactory() {

	}

	private static IStudentService studentService;

	public static IStudentService getStudentService() {
		if (studentService == null)
			studentService = new StudentServiceImpl();
		return studentService;

	}
}
