package org.sparta.spartaassignment2.repository;

import org.sparta.spartaassignment2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
