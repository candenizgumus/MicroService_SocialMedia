package com.candenizgumus.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@MappedSuperclass
public class BaseEntity {


    @Builder.Default
    private Long createAt = System.currentTimeMillis();
    @Builder.Default
    private Long updateAt = System.currentTimeMillis();
    @Builder.Default
    private Boolean state = true;


}