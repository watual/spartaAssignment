package org.sparta.spartaassignment2.repository;

import org.sparta.spartaassignment2.dto.ScheduleResponseDto;
import org.sparta.spartaassignment2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc();

//    @Transactional
//    @Modifying
//    @Query("UPDATE Schedule SET title = :title, contents = :contents, manager = :manager, modifiedAt = :modifiedAt WHERE id = :id")
//    void updateSchedule(Long id, String title, String contents, String manager, LocalDateTime modifiedAt);
}

