package org.sparta.spartaassignment2.service;

import org.sparta.spartaassignment2.dto.ScheduleRequestDto;
import org.sparta.spartaassignment2.dto.ScheduleResponseDto;
import org.sparta.spartaassignment2.entity.Schedule;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto);
        scheduleRepository.save(schedule);
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getScheduleAll() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }
}
