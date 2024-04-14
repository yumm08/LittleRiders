package com.example.rdb_with_mongo.mongo.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "book_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    private String id;

    private Long memberId;

    @Getter
    private List<Long> bookIdList;


    private History(Long memberId, List<Long> bookIdList) {
        this.memberId = memberId;
        this.bookIdList = bookIdList;
    }

    public static History of(Long memberId, List<Long> bookIdList){
        return new History(memberId,bookIdList);
    }


    @Override
    public String toString() {
        return "History{" +
                "id='" + id + '\'' +
                ", memberId=" + memberId +
                ", bookIdList=" + bookIdList +
                '}';
    }
}
