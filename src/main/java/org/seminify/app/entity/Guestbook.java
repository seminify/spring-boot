package org.seminify.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long gno;
    @Column(length = 100, nullable = false)
    @Setter
    private String title;
    @Column(length = 1500, nullable = false)
    @Setter
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;
}
