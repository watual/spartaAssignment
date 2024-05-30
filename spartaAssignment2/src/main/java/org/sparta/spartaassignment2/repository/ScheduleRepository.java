package org.sparta.spartaassignment2.repository;

import org.sparta.spartaassignment2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc();

    Optional<Schedule> findByManager(String manager);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Schedule SET title = :title, contents = :contents, manager = :manager, modifiedAt = :modifiedAt WHERE id = :id")
//    void updateSchedule(Long id, String title, String contents, String manager, LocalDateTime modifiedAt);
}