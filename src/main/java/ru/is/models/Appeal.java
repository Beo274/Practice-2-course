package ru.is.models;

import jakarta.persistence.*;

@Entity
@Table(name = "appeals")
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String applicantName;

    @Column(nullable = false)
    private String managerName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String resolution;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = true)
    private String note;

    @Lob
    private String qrCodeBase64;

    public String getQrCodeBase64() {
        return qrCodeBase64;
    }

    public void setQrCodeBase64(String qrCodeBase64) {
        this.qrCodeBase64 = qrCodeBase64;
    }

    public Appeal(Long id, String applicantName, String managerName, String address, String theme, String content, String resolution, boolean status, String note) {
        this.id = id;
        this.applicantName = applicantName;
        this.managerName = managerName;
        this.address = address;
        this.theme = theme;
        this.content = content;
        this.resolution = resolution;
        this.status = status;
        this.note = note;
    }

    public Appeal() {
    }

    public Long getId() {
        return id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getAddress() {
        return address;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }

    public String getResolution() {
        return resolution;
    }

    public boolean getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
