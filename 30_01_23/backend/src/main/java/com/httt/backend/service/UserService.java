package com.httt.backend.service;

import com.httt.backend.dto.UserExport;
import com.httt.backend.entity.Intake;
import com.httt.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    //    List<User> getAllUsers();
//    List<User> findAllUsersByPage(Integer pageNumber, Integer pageSize, String sortBy);
    Optional<User> getUserByUsername(String username);

    String getUserName();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Page<User> findUsersByPage(Pageable pageable);

    Page<User> findUsersDeletedByPage(Pageable pageable, boolean deleted);

    Page<User> findAllByDeletedAndUsernameContains(boolean deleted, String username, Pageable pageable);


    User createUser(User user);

    User createUserHaveIntake(User user, Intake intake);

    Optional<User> findUserById(Long id);

    List<UserExport> findAllByDeletedToExport(boolean statusDelete);

    void updateUser(User user);

    List<User> findAllByIntakeId(Long id);

//    List<User> findAllByExamId(Long id);

    Page<User> findUsersByExam(Long id, Pageable pageable);

    Page<User> findAllUserByExam(Long id, Pageable pageable);


    boolean requestPasswordReset(String email) throws MessagingException;

    boolean resetPassword(String token, String password);

    public Page<User> findAllByUsernameContainsOrEmailContains(String username, String email, Pageable pageable);


}
