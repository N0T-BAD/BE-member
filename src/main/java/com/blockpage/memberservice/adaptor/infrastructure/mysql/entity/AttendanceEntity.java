package com.blockpage.memberservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.memberservice.domain.Attendance;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance")
public class AttendanceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private Boolean checks;

    public static AttendanceEntity postAttendance(Attendance attendance) {
        return AttendanceEntity.builder()
            .memberEmail(attendance.getMemberEmail())
            .checks(true)
            .build();
    }
}
