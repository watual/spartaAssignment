package org.sparta.spartaassignment2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sparta.spartaassignment2.dto.ScheduleRequestDto;
import org.sparta.spartaassignment2.dto.ScheduleResponseDto;
import org.sparta.spartaassignment2.entity.Schedule;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.sparta.spartaassignment2.service.ScheduleService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@DisplayName("스케쥴 서비스 테스트")
public class ScheduleServiceTest {

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    ScheduleService scheduleService;

    @Test
    @DisplayName("테스트: 스케쥴 생성")
    public void test_findAll_findById() {
        // getAll 과 getById 가 일치하는지 Test
        // given
        // 임시 requestDto 생성
        ScheduleRequestDto requestDto = ScheduleRequestDto.builder()
                .title("제목 테스트1")
                .contents("내용 테스트1")
                .manager("매니저테스트1@gmail.com")
                .password("123a")
                .build();
        // requestDto 변환 값
        Schedule schedule = new Schedule(requestDto);

        // 만들어져야하는 responseDto
        ScheduleResponseDto responseDto = ScheduleResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .contents(schedule.getContents())
                .manager(schedule.getManager())
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
        // mock 객체 stubbing
//        given(scheduleRepository.save(any(Schedule.class))).willReturn(schedule);

        // when
        // 서비스 실행으로 얻은 테스트 값
//        ScheduleResponseDto newScheduleResponseDto = scheduleService.createSchedule(requestDto);

        // then
        Assertions.assertEquals(responseDto.getTitle(), "제목 테스트1");
        Assertions.assertEquals(responseDto.getContents(), "내용 테스트1");
        Assertions.assertEquals(responseDto.getManager(), "매니저테스트1@gmail.com");

    }
}