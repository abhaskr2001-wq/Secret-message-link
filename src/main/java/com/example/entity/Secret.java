package com.example.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "secrets")
public class Secret {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private boolean used = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public Secret() {

    }

    public Secret(String token, String value) {
        this.token = token;
        this.value = value;
        this.used = false;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getValue() {
        return value;
    }

    public boolean isUsed() {
        return used;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void markAsUsed() {
        this.used = true;
    }
}
