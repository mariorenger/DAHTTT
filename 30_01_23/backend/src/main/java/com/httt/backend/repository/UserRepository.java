package com.httt.backend.repository;

import com.httt.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    Boolean existsByEmailOrUsername(String email, String username);

    public Page<User> findAll(Pageable pageable);

    public Page<User> findAllByDeleted(boolean deleted, Pageable pageable);

    public Page<User> findAllByDeletedAndUsernameContains(boolean deleted, String username, Pageable pageable);
    public Page<User> findAllByUsernameContainsOrEmailContains(String username, String email, Pageable pageable);

    //    public Page<User> findUsersByDeletedAndUsernameIsContainingOrEmailIsContaining(boolean deleted, String username, String email, Pageable pageable);
    List<User> findAllByDeleted(boolean statusDeleted);

    List<User> findAllByIntakeId(Long id);

//    @Query(value="select * from users c join exam_user p on c.id = p.user_id where p.exam_id = ?1",
//            countQuery = "select count(*) from users c join exam_user p on c.id = p.user_id where p.exam_id = ?1",
//            nativeQuery=true)
    @Query(value="select * from users c join course_intake p on c.intake_id = p.intake_id where p.course_id = ?1",
            countQuery = "select count(*) from users c join course_intake p on c.intake_id = p.intake_id where p.course_id = ?1",
            nativeQuery=true)
    Page<User> findListUserByExamId(Long id, Pageable pageable);


    @Query(value="select * from users c join exam_user p on c.id = p.user_id where p.exam_id = ?1",
            countQuery = "select count(*) from users c join exam_user p on c.id = p.user_id where p.exam_id = ?1",
            nativeQuery=true)
    Page<User> findAllUserByExamId(Long id, Pageable pageable);

    List<User> findByDeletedIsFalseOrderByCreatedDateDesc();

}
