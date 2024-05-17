package org.sparta.spartaassignment2.service;

import lombok.NoArgsConstructor;
import org.apache.tika.Tika;
import org.sparta.spartaassignment2.dto.SchedulePasswordRequestDto;
import org.sparta.spartaassignment2.dto.ScheduleRequestDto;
import org.sparta.spartaassignment2.dto.ScheduleResponseDto;
import org.sparta.spartaassignment2.entity.Schedule;
import org.sparta.spartaassignment2.error.FileExtensionException;
import org.sparta.spartaassignment2.error.FileStorageException;
import org.sparta.spartaassignment2.error.WrongPasswordException;
import org.sparta.spartaassignment2.error.WrongWayException;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor(force = true)
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // application.properties 에 app.upload.dir을 정의하고, 없는 경우에 default 값으로 user.home (System에 종속적인)
    @Value("${upload.dir:${user.home}}")
    private String uploadDir;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        nullCheck(requestDto);
        Schedule schedule = new Schedule(requestDto);   // 요청받은 스케쥴entity 7
        scheduleRepository.save(schedule);  //스케쥴entity -> DB보냄
        // DB에서 스케쥴entity 가져옴 -> schedule2
        return new ScheduleResponseDto(scheduleRepository.getReferenceById(schedule.getId()));   //schedule2 -> ResponseDto 반환
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.getReferenceById(id);
        return new ScheduleResponseDto(schedule);
    }

    public List<ScheduleResponseDto> getScheduleAll() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto modifySchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        if (!requestDto.getPassword().equals(schedule.getPassword())) {
            throw new WrongPasswordException("틀린 비밀번호입니다.");
        }
        schedule.update(requestDto);

        Schedule schedule1 = new Schedule(requestDto);
        scheduleRepository.save(schedule1); // DB에 저장이 됩니다!
        return new ScheduleResponseDto(findSchedule(id));
    }

    public Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("스케쥴이 존재하지 않습니다.")
        );
    }

    public void deleteSchedule(SchedulePasswordRequestDto schedulePasswordRequestDto) {
        Long id = schedulePasswordRequestDto.getId();
        String password = schedulePasswordRequestDto.getPassword();
        if (!password.equals(findSchedule(id).getPassword())) {
            throw new WrongPasswordException("틀린 비밀번호입니다.");
        }
        scheduleRepository.deleteById(id);
    }

    public void fileUpload(MultipartFile multipartFile) {
        // File.seperator 는 OS종속적이다.
        // Spring에서 제공하는 cleanPath()를 통해서 ../ 내부 점들에 대해서 사용을 억제한다
        Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        try {
            InputStream inputStream = multipartFile.getInputStream();
            validImgFile(inputStream);
            // inputStream 을 가져와서
            // copyOfLocation (저장위치)로 파일을 쓴다.
            // copy 의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
            Files.copy(inputStream, copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file : " + multipartFile.getOriginalFilename(), e);
        }
    }

    // 파일 확장자 점검
    private static final Tika tika = new Tika();

    public static void validImgFile(InputStream inputStream) throws IOException {
        List<String> notValidTypeList = Arrays.asList("image/jpeg", "image/png", "image/gif", "image/bmp", "image/tiff");
        String mimeType = tika.detect(inputStream);
        if (notValidTypeList.stream().anyMatch(notValidType -> notValidType.equalsIgnoreCase(mimeType))) {
            throw new FileExtensionException("이미지 파일이 아닙니다.");
        }
    }
    public static void nullCheck(Object obj) {
        if (
                (obj == null) ||
                ((obj instanceof String) && (((String)obj).trim().length() == 0)) ||
                (obj instanceof Map) &&  (((Map<?, ?>)obj).isEmpty()) ||
                (obj instanceof List) && ((List<?>)obj).isEmpty() ||
                (obj instanceof Object[]) && (((Object[])obj).length == 0)
        ){
            throw new WrongWayException("Null 값이 들어와선 안됩니다.");
        }
    }

}
