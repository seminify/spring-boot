package org.seminify.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_memo")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@ToString
public class Memo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long mno;
    @Column(length = 200, nullable = false)
    private String memoText;
}
