package com.example.repository;


import com.example.entity.Secret;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SecretRepository implements PanacheRepository<Secret> {
}
