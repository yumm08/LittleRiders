package com.example.rdb_with_mongo;

import com.example.rdb_with_mongo.mongo.entity.History;
import com.example.rdb_with_mongo.mongo.entity.HistoryRepository;
import com.example.rdb_with_mongo.rdb.entity.Book;
import com.example.rdb_with_mongo.rdb.entity.BookRepository;
import com.example.rdb_with_mongo.rdb.entity.Member;
import com.example.rdb_with_mongo.rdb.entity.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RdbWithMongoApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HistoryRepository historyRepository;


    @Test
    @DisplayName("테스트 시작")
    void start(){


        System.out.println("MONGO DB 초기화 진행");
        removeHistory();
        System.out.println("RDB 에 book 삽입");
        insertBook();
        System.out.println("RDB 에 member 삽입");
        insertMember();
        System.out.println("김씨가 docker 와 jpa 를 빌린다.");
        rent();
        System.out.println("김씨가 빌린 책을 mongo 에서 조회후, RDB 에서 검증한다.");
        show();
    }


    @DisplayName("초기화 진행")
    void removeHistory(){
        historyRepository.deleteAll();
    }

    void insertBook(){
        String[] bookList = new String[] {"docker","mongo","jpa","window","linux"};
        for (String title : bookList){
            Book book = Book.from(title);
            bookRepository.save(book);
        }
    }

    void insertMember(){
        String[] nameList = new String[]{"김씨","박씨","최씨"};
        for (String name : nameList){
            Member member = Member.from(name);
            memberRepository.save(member);
        }
    }


    void rent(){
        Member member = memberRepository.findByName("김씨");
        Book docker = bookRepository.findByTitle("docker");
        Book mongo = bookRepository.findByTitle("jpa");

        List<Long> bookIdList = new ArrayList<>();
        bookIdList.add(docker.getId());
        bookIdList.add(mongo.getId());
        History history = History.of(member.getId(), bookIdList);

        historyRepository.save(history);

    }


    void show(){
        Member member = memberRepository.findByName("김씨");
        History history = historyRepository.findByMemberId(member.getId());

        List<Long> bookIdList = history.getBookIdList();
        for (Long bookId : bookIdList){
            System.out.println(bookRepository.findById(bookId));
        }

    }
}
