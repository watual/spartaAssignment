package org.sparta.spartaassignment2.controller;

import jakarta.validation.Valid;
import org.sparta.spartaassignment2.dto.SchedulePasswordRequestDto;
import org.sparta.spartaassignment2.dto.ScheduleRequestDto;
import org.sparta.spartaassignment2.dto.ScheduleResponseDto;
import org.sparta.spartaassignment2.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 작성
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 선택한 일정 조회
    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    // 전체 일정 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getScheduleAll() {
        return scheduleService.getScheduleAll();
    }

    // 선택한 일정 수정
    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto modifySchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.modifySchedule(id, requestDto);
    }

    // 선택한 일정 삭제
    @DeleteMapping("/schedule")
    public void deleteSchedule(@Valid @RequestBody SchedulePasswordRequestDto schedulePasswordRequestDto) {
        scheduleService.deleteSchedule(schedulePasswordRequestDto);
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        scheduleService.fileUpload(file);
        return "You successfully uploaded " + file.getOriginalFilename() + "!";
    }
}
