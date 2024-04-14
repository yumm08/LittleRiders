package com.example.rdb_with_mongo.rdb.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name="title")
    private String title;




    private Book(String title){
        this.title = title;
    }

    public static Book from(String title){
        return new Book(title);
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
