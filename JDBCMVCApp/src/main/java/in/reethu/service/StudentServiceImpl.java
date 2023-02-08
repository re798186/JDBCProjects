package in.reethu.service;

import in.reethu.dao.IStudentDao;
import in.reethu.dto.Student;
import in.reethu.factory.StudentDaoFactory;

public class StudentServiceImpl implements IStudentService {
	IStudentDao studentDao;
	@Override
	public String save(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.save(student);
	}

	@Override
	public Student findById(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.findById(sid);
	}

	@Override
	public String deleteById(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteById(sid);
	}

	@Override
	public String updateById(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateById(student);
	}

}
