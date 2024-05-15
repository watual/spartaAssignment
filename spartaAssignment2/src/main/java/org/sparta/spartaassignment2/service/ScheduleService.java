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

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.getReferenceById(id);
        return new ScheduleResponseDto(schedule);
    }

    public List<ScheduleResponseDto> getScheduleAll() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto modifySchedule(Long id, ScheduleRequestDto requestDto) {
        if (requestDto.getPassword().equals(findSchedule(id).getPassword())) {
            Schedule schedule = findSchedule(id);
            schedule.update(requestDto);
            scheduleRepository.save(schedule);
            return new ScheduleResponseDto(findSchedule(id));
        } else {
            throw new IllegalArgumentException("틀린 비밀번호입니다.");
        }
    }

    public Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("스케쥴이 존재하지 않습니다.")
        );
    }
}
