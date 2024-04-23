package kr.co.littleriders.backend.domain.station.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "station",
uniqueConstraints = @UniqueConstraint(
        name = "station_latitude_longitude_unique",
        columnNames = {"academy_id","latitude","longitude"}
))
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "academy_id")
//    private Academy academy;

    @Column(name = "academy_id",nullable = false)
    private Long academy;


    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "latitude",nullable = false)
    private double latitude;

    @Column(name = "longitude",nullable = false)
    private double longitude;
}
