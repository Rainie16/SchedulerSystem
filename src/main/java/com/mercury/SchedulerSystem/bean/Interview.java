package com.mercury.SchedulerSystem.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Scheduler_Interview")
public class Interview {

    @Id
    @SequenceGenerator(name = "scheduler_interview_seq_gen", sequenceName = "SCHEDULER_INTERVIEW_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "scheduler_interview_seq_gen")
    private int id;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date appointment;

    @Column
    private String candidate;

    @Column
    private String phone;

    @Column
    private String scheduler;

    @Column
    private String status;

    @Column
    private String resume;

    @Column
    private String comments;

    @Column
    private String email;

    public Interview() {
    }

    public Interview(int id, Date appointment, String candidate, String phone, String scheduler, String status, String resume, String comments, String email) {
        this.id = id;
        this.appointment = appointment;
        this.candidate = candidate;
        this.phone = phone;
        this.scheduler = scheduler;
        this.status = status;
        this.resume = resume;
        this.comments = comments;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(Date appointment) {
        this.appointment = appointment;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getScheduler() {
        return scheduler;
    }

    public void setScheduler(String scheduler) {
        this.scheduler = scheduler;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", appointment=" + appointment +
                ", candidate='" + candidate + '\'' +
                ", phone='" + phone + '\'' +
                ", scheduler='" + scheduler + '\'' +
                ", status='" + status + '\'' +
                ", resume='" + resume + '\'' +
                ", comments='" + comments + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
