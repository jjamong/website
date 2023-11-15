package com.jjamong.hormone.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@Table(name = "user")
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @NonNull
    @Column(unique = true)
    private String userId;
    @NonNull
    private String userPw;
    @NotNull
    @Column(unique = true)
    private String userPhone;
    @NotNull
    private String userName;
    @NotNull
    private String userAddress1;
    @NotNull
    private String userAddress2;
    @NotNull
    private String userAddress3;

    private LocalDateTime rgstDate;
    private String rgstId;
    @JsonIgnore
    private LocalDateTime mdfyDate;
    @JsonIgnore
    private String mdfyId;

    private LocalDateTime loginDate;

    private String role;

    @Column(columnDefinition = "TINYINT", length = 1)
    @ColumnDefault("0")
    private int status;

    public UserEntity(String userId, String userName, LocalDateTime rgstDate, String role) {
        this.userId = userId;
        this.rgstDate = rgstDate;
        this.role = role;
    }

}