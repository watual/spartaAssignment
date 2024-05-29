package org.sparta.spartaassignment2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sparta.spartaassignment2.dto.ScheduleRequestDto;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Schedule extends Timestamped {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 1000)
    private String contents;
    @Column(nullable = false)
    private String manager;
    @Column(nullable = false)
    private String password;

    // 하나의 스케쥴에 다수의 댓글이 달리므로 댓글N : 스케쥴1 관계 설정
    @OneToMany(mappedBy = "schedule_id")
    private List<Comment> commentList;

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
    }
}
