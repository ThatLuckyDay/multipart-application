package org.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.net.InetAddress;
import java.time.OffsetDateTime;

@Entity
@Table(name = "models", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "createdate")
    private OffsetDateTime createDate;

    @Column(name = "reference")
    private InetAddress reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person person;
}
