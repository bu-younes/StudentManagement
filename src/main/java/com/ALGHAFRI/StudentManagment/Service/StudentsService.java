package com.ALGHAFRI.StudentManagment.Service;

import com.ALGHAFRI.StudentManagment.Models.Students;
import com.ALGHAFRI.StudentManagment.Repository.StudentsRepository;
import com.ALGHAFRI.StudentManagment.Request.StudentsRequest;
import com.ALGHAFRI.StudentManagment.ResourceNotFoundException;
import com.ALGHAFRI.StudentManagment.Response.StudentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public StudentsResponse createStudent(StudentsRequest request) {
        Students student = new Students();
        student.setName(request.getName());
        student.setRollNumber(request.getRollNumber());

        Students savedStudent = studentsRepository.save(student);

        StudentsResponse response = new StudentsResponse();
        response.setStudent_id(savedStudent.getId());
        response.setStudent_name(savedStudent.getName());
        response.setRollNumber(savedStudent.getRollNumber());

        return response;
    }

    public List<StudentsResponse> getAllStudents() {
        List<Students> students = studentsRepository.findAll();
        List<StudentsResponse> responseList = new ArrayList<>();

        for (Students student : students) {
            StudentsResponse response = new StudentsResponse();
            response.setStudent_id(student.getId());
            response.setStudent_name(student.getName());
            response.setRollNumber(student.getRollNumber());
            responseList.add(response);
        }

        return responseList;
    }


    public StudentsResponse getStudentById(Long studentId) {
        Optional<Students> studentOptional = studentsRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Students student = studentOptional.get();
            StudentsResponse response = new StudentsResponse();
            response.setStudent_id(student.getId());
            response.setStudent_name(student.getName());
            response.setRollNumber(student.getRollNumber());
            return response;
        } else {
            // Handle the case where the student with the given ID is not found
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }
    }



    public StudentsResponse updateStudentById(Long studentId, StudentsRequest request) {
        Optional<Students> studentOptional = studentsRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Students student = studentOptional.get();
            student.setName(request.getName());
            student.setRollNumber(request.getRollNumber());

            Students updatedStudent = studentsRepository.save(student);

            StudentsResponse response = new StudentsResponse();
            response.setStudent_id(updatedStudent.getId());
            response.setStudent_name(updatedStudent.getName());
            response.setRollNumber(updatedStudent.getRollNumber());
            return response;
        } else {
            // Handle the case where the student with the given ID is not found
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }
    }


    public String deleteStudentById(Long studentId) {
        Optional<Students> studentOptional = studentsRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            studentsRepository.deleteById(studentId);
            return "Student with ID " + studentId + " has been deleted successfully";
        } else {
            // Handle the case where the student with the given ID is not found
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }
    }
}
