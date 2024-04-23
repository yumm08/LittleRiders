package kr.co.littleriders.backend.domain.route.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

import java.util.List;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id",nullable = false)
    private Academy academy;

    @Column(name = "name",nullable = false)
    private String name;

//    @OneToMany
//    private List<Object> stopRouteList;



}
