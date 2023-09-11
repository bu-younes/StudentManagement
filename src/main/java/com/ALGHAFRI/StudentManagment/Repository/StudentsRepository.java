package com.ALGHAFRI.StudentManagment.Repository;

import com.ALGHAFRI.StudentManagment.Models.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository  extends JpaRepository<Students, Long> {
}
