package in.reethu.factory;

import in.reethu.controller.IStudentController;
import in.reethu.controller.StudentControllerImpl;

public class StudentControllerFactory {
	private StudentControllerFactory() {

	}

	private static IStudentController studentController;

	public static IStudentController getStudentController() {
		if (studentController == null)
			studentController = new StudentControllerImpl();
		return studentController;

	}
}
