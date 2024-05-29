package org.sparta.spartaassignment2.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment extends Timestamped {
    @Id
    private Long commentId;
    private String text;
    private String manager;

    // 하나의 스케쥴에 다수의 댓글이 달리므로 댓글N : 스케쥴1 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}