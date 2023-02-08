package in.reethu.factory;

import in.reethu.dao.IStudentDao;
import in.reethu.dao.StudentDaoImpl;


public class StudentDaoFactory {
	private StudentDaoFactory() {

	}

	private static IStudentDao studentDao;

	public static IStudentDao getStudentDao() {
		if (studentDao == null)
			studentDao = new StudentDaoImpl();
		return studentDao;

	}
}
