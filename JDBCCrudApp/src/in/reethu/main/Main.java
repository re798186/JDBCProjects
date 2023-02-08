package in.reethu.main;

import java.io.*;

import in.reethu.controller.IStudentController;
import in.reethu.dto.Student;
import in.reethu.factory.StudentControllerFactory;

public class Main {

	public static void main(String[] args) {
		try {
			System.out.println("WELCOME TO JDBC OPERTAIONS");
			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				IStudentController studentController = StudentControllerFactory.getStudentController();
				System.out.println("1: Save new student");
				System.out.println("2: Find student");
				System.out.println("3: Update student");
				System.out.println("4: Delete student");
				System.out.println("5: Exit");
				System.out.print("Please enter your selection from 1-5: ");
				int option = Integer.parseInt(br.readLine());
				// TODO Auto-generated catch block
				Student studentRecord;
				switch (option) {
				case 1:
					System.out.println("Please enter the details");
					System.out.print("Student Name :");
					String sname = br.readLine();
					System.out.print("Student Age :");
					int sage = Integer.parseInt(br.readLine());
					System.out.print("Student Address :");
					String saddr = br.readLine();
					Student student = new Student();
					student.setSname(sname);
					student.setSage(sage);
					student.setSaddr(saddr);
					String status = studentController.save(student);
					if (status.equalsIgnoreCase("success")) {
						System.out.println("Record inserted succesfully...");
					} else if (status.equalsIgnoreCase("failure")) {
						System.out.println("Record insertion failed...");
					} else {
						System.out.println("Some problem occured...");
					}
					break;
				case 2:
					System.out.print("Please enter the student id :");
					int sid = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(sid);
					if (studentRecord != null)
						System.out.println(studentRecord);
					else
						System.out.println("Record not available for the given id ::" + sid);

					break;
				case 3:
					System.out.print("Please enter the student id :");
					int sidUp = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(sidUp);
					if (studentRecord != null) {

						Student newStudent = new Student();
						newStudent.setSid(sidUp);

						System.out.print("StudentName ::[Old Name is :: " + studentRecord.getSname() + "]:  ");
						String newName = br.readLine();
						if (newName == null || newName.equals("")) {
							newStudent.setSname(studentRecord.getSname());
						} else {
							newStudent.setSname(newName);
						}

						System.out.print("StudentAge ::[Old Age is :: " + studentRecord.getSage() + "]: ");
						String snewAge =br.readLine();
						if (snewAge.equals("")) {
							newStudent.setSage(studentRecord.getSage());
						} else {
							Integer newAge = Integer.parseInt(snewAge);
							newStudent.setSage(newAge);
						}

						System.out.print("StudentAddress ::[Old Address is :: " + studentRecord.getSaddr() + "]:  ");
						String newAddr = br.readLine();
						if (newAddr == null || newAddr.equals("")) {
							newStudent.setSaddr(studentRecord.getSaddr());
						} else {
							newStudent.setSaddr(newAddr);
						}

						status = studentController.updateById(newStudent);
						if (status.equalsIgnoreCase("success")) {
							System.out.println("Record updated succesfully...");
						} else if (status.equalsIgnoreCase("failure")) {
							System.out.println("Record updation failed...");
						} else {
							System.out.println("Some problem occured...");
						}
					}else
						System.out.println("Record not available for the given id ::" + sidUp);
					break;

				case 4:
					System.out.print("Please enter the student id to delete:");
					int sidDel = Integer.parseInt(br.readLine());
					String delStatus = studentController.deleteById(sidDel);
					if (delStatus.equalsIgnoreCase("success")) {
						System.out.println("Record deleted succesfully...");
					} else if (delStatus.equalsIgnoreCase("failure")) {
						System.out.println("Record deletion failed...");
					} else {
						System.out.println("Record not available to delete..");
					}
					break;

				case 5:
					System.out.println("Thank for using the application");
					System.exit(0);
				default:
					System.out.println("Please choose option from 1-5");

				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

}
