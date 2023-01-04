package com.example.demo;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", allocationSize = 100, sequenceName = "s_user")
    private Long id;

    @Column(name = "email", columnDefinition = "varchar(255) not null", nullable = false)
    @Convert(converter = EmailConverter.class)
    @NaturalId
    private Email email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }
}
