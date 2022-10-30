package com.student.service;

import com.student.exception.InSufficientAttendenceException;
import com.student.exception.StudentAdmissionException;
import com.student.exception.StudentClassPromotionException;
import com.student.exception.StudentMarksException;
import com.student.exception.StudentNotFoundException;

public class MyStudentManager implements StudentManager {

	@Override
	public Boolean findStudent(String id) throws StudentNotFoundException {
		if (id.equalsIgnoreCase("108")) {
			throw new StudentNotFoundException("Invalid Student ID");
		}
		return true;
	}

	@Override
	public String checkAttendance(String id) throws InSufficientAttendenceException, StudentNotFoundException {
		Boolean b = false;
		try {
			b = findStudent(id);
		} catch (StudentNotFoundException ste) {
			throw new StudentNotFoundException("Invalid Student ID");
		}
		
		if(id.contains("125")) {
			throw new InSufficientAttendenceException("Attendance is not sufficient");
		}
		return null;
	}

	@Override
	public String Marks(String id, String grade) throws StudentAdmissionException, StudentNotFoundException, InSufficientAttendenceException, StudentClassPromotionException, StudentMarksException {
		Boolean b = false;
		String mess="";
		try {
			b = findStudent(id);
		} catch (StudentNotFoundException snf) {
			 throw new StudentMarksException("Invalid Student ID");
		}
		
		try {
			mess = checkAttendance(id);	
			} catch (InSufficientAttendenceException ise) {
				throw new StudentMarksException("Attendance is not sufficient");
			}
		
			if(grade.equalsIgnoreCase("third")) {
				throw new StudentMarksException("Student is failed");
			}
		
			return null;
		}
		
	           
	@Override
	public String StudentPromotion(String id, String grade) throws StudentAdmissionException, StudentNotFoundException, InSufficientAttendenceException, StudentClassPromotionException, StudentMarksException {

        Boolean b = false;
        String mess = "";
        
        try {
			b = findStudent(id);
		} catch (StudentNotFoundException snf) {
			 throw new StudentClassPromotionException("Invalid Student ID");
		}
		
		try {
			mess = checkAttendance(id);	
			} catch (InSufficientAttendenceException ise) {
				throw new StudentClassPromotionException("Attendance is not sufficient");
			}
        try {
        	mess = Marks(id, grade);
        } catch (StudentClassPromotionException spe) {
			throw new StudentClassPromotionException("Student is not promoted");
		}
        
		return id.concat(" "+grade);
	}

	
}
