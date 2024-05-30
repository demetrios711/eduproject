package org.euproject.eduproject.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String certification;

    @Column(columnDefinition = "TEXT")
    private String comments;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName = "id", nullable = true)
    private Account account;

}
