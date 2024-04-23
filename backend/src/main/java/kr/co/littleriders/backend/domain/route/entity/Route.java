package kr.co.littleriders.backend.domain.route.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


//    @ManyToOne
//    @JoinColumn(name = "academy_id",nullable = false)
//    private Academy academy;

    @Column(name = "academy_id",nullable = false)
    private Long academy;

    @Column(name = "name",nullable = false)
    private String name;

//    @OneToMany
//    private List<Object> stopRouteList;



}
