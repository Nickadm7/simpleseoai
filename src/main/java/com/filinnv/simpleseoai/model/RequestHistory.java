package com.filinnv.simpleseoai.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requesthistory")
public class RequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="created")
    private LocalDateTime created;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;
}
