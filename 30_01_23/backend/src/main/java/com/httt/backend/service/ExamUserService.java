package com.httt.backend.service;

import com.httt.backend.entity.Exam;
import com.httt.backend.entity.ExamUser;
import com.httt.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExamUserService {
    void create(Exam exam, List<User> userSet);
//    void addUsertoExam(Exam exam, List<User> userSet);
    void addUsertoExam(Exam exam, User user);
//    List<User> getUserListByExamId(Long id);
//    public Page<User> getUserListByExamId(Long id, Pageable pageable);
//    public Page<User> getAllUserByExamId(Long id, Pageable pageable);
    List<ExamUser> getExamListByUsername(String username);
    ExamUser findByExamAndUser(Long examId, String username);
    void update(ExamUser examUser);
    Optional<ExamUser> findExamUserById(Long id);

    List<ExamUser> getCompleteExams(Long courseId, String username);
    List<ExamUser> findAllByExam_Id(Long examId);
    List<ExamUser> findExamUsersByIsFinishedIsTrueAndExam_Id(Long examId);


}
