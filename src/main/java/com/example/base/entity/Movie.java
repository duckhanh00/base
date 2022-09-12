package com.example.base.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

// data la day du nhat, khong dung data thi se dung builder getter setter
@Entity
@Table(name="MOVIE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {
    @Id
    @Column(name="ID", nullable = false)
    private Long movieId;

    @Column(name="MOVIE_NAME")
    private String movieName;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CREATE_BY")
    private String createBy;

    @Column(name="CREATE_AT")
    private Date createAt;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="MODIFIED_AT")
    private Date modifiedAt;
}
