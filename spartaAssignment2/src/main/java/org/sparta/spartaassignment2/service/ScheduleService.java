package org.sparta.spartaassignment2.service;

import org.sparta.spartaassignment2.dto.ScheduleResponseDto;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(){
        return null;
    }
}
