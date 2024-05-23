package com.candenizgumus.entities;

import com.candenizgumus.enums.Role;
import com.candenizgumus.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblauth")
public class Auth extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    String password;
    String email;
    @Enumerated(EnumType.STRING)
    Role role;
    String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    Status status = Status.PENDING;

    String emailResetCode;




}
