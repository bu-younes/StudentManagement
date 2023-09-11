package com.ALGHAFRI.StudentManagment.Controller;

import com.ALGHAFRI.StudentManagment.Request.StudentsRequest;
import com.ALGHAFRI.StudentManagment.Response.StudentsResponse;
import com.ALGHAFRI.StudentManagment.Service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")

public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public StudentsResponse createStudent(@RequestBody StudentsRequest request) {
        return studentsService.createStudent(request);
    }

    @GetMapping
    public List<StudentsResponse> getAllStudents() {
        return studentsService.getAllStudents();
    }

    @GetMapping("/{student_id}")
    public StudentsResponse getStudentById(@PathVariable("student_id") Long studentId) {
        return studentsService.getStudentById(studentId);
    }

    @PutMapping("/{student_id}")
    public StudentsResponse updateStudentById(@PathVariable("student_id") Long studentId, @RequestBody StudentsRequest request) {
        return studentsService.updateStudentById(studentId, request);
    }


    @DeleteMapping("/{student_id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("student_id") Long studentId) {
        String message = studentsService.deleteStudentById(studentId);
        return ResponseEntity.ok(message);
    }

}
