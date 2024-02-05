package org.seminify.app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class SampleDTO {
    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
}
