package com.youube.clone.youtubeclone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.youube.clone.youtubeclone.module.video;
import com.youube.clone.youtubeclone.repository.videoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class videoService {
	
	private final S3Service s3Service;
	private final videoRepository videoRepository;

	public void uploadVideo(MultipartFile file) {
		// upload file to S3
		String videoUrl = s3Service.uploadFile(file);
		
		video video = new video();
		video.setVideoUrl(videoUrl);
		
		// save video data to db
		videoRepository.save(video);
	}
}
