package in.reethu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.reethu.dto.Student;
import in.reethu.util.JdbcUtils;

public class StudentDaoImpl implements IStudentDao {

	Connection conn = null;

	
	@Override
	public String save(Student student) {
		PreparedStatement pstmt = null;
		String status = "Failure";
		try {
			conn = JdbcUtils.getJdbcConnection();
			String sqlQuery = "insert into student(`sname`,`sage`,`saddr`) values(?,?,?) ";
			if (conn != null) {
				pstmt = conn.prepareStatement(sqlQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddr());
				int noOfRows = pstmt.executeUpdate();
				if(noOfRows>0)
					status = "Success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student findById(Integer sid) {

		PreparedStatement pstmt = null;
		Student student = null;
		try {
			conn = JdbcUtils.getJdbcConnection();
			String sqlQuery = "select * from student where sid=? ";
			if (conn != null) {
				pstmt = conn.prepareStatement(sqlQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				ResultSet res = pstmt.executeQuery();
				if(res.next()) {
					student = new Student();
					student.setSid(res.getInt(1));
					student.setSname(res.getString(2));
					student.setSage(res.getInt(3));
					student.setSaddr(res.getString(4));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String deleteById(Integer sid) {
		PreparedStatement pstmt = null;
		String status = "Failure";
		try {
			conn = JdbcUtils.getJdbcConnection();
			String sqlQuery = "delete from student where sid=? ";
			if (conn != null) {
				pstmt = conn.prepareStatement(sqlQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				int noOfRows = pstmt.executeUpdate();
				if(noOfRows>0)
					status = "Success";
				else
					status = "Not Available";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String updateById(Student student) {
		PreparedStatement pstmt = null;
		String status = "Failure";
		try {
			conn = JdbcUtils.getJdbcConnection();
			String sqlQuery = "update student set sname=?, sage=?, saddr=? where sid =?";
			if (conn != null) {
				pstmt = conn.prepareStatement(sqlQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddr());
				pstmt.setInt(4, student.getSid());
				int noOfRows = pstmt.executeUpdate();
				if(noOfRows>0)
					status = "Success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	
	}

}
