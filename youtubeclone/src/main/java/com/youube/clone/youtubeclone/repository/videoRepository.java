package com.youube.clone.youtubeclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.youube.clone.youtubeclone.module.video;

public interface videoRepository extends MongoRepository<video, String>{

}
