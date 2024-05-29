package org.sparta.spartaassignment2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sparta.spartaassignment2.dto.CommentRequestDto;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Comment extends TimestampedOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String text;
    @Column
    private String manager;

    // 하나의 스케쥴에 다수의 댓글이 달리므로 댓글N : 스케쥴1 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, Schedule schedule) {
        this.text = requestDto.getText();
        this.manager = requestDto.getManager();
        this.schedule = schedule;
    }
}