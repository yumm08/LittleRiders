package com.example.rdb_with_mongo.mongo.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoryRepository extends MongoRepository<History,String> {
    History findByMemberId(Long memberId);
}
