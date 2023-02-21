package com.youube.clone.youtubeclone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.youube.clone.youtubeclone.dto.uploadVideoResponse;
import com.youube.clone.youtubeclone.dto.videoDto;
import com.youube.clone.youtubeclone.module.video;
import com.youube.clone.youtubeclone.repository.videoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class videoService {

	private final S3Service s3Service;
	private final videoRepository videoRepository;

	private video getVideoById(String videoId) {
		return videoRepository.findById(videoId)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find video by Id - " + videoId));
	}

	public uploadVideoResponse uploadVideo(MultipartFile file) {
		// upload file to S3
		String videoUrl = s3Service.uploadFile(file);

		video video = new video();
		video.setVideoUrl(videoUrl);

		// save video data to db
		video savedVideo = videoRepository.save(video);

		return new uploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
	}

	public videoDto editVideo(videoDto videoDto) {
		// find video by id
		video savedVideo = getVideoById(videoDto.getId());

		// Add Dto fields
		savedVideo.setTitle(videoDto.getTitle());
		savedVideo.setDescription(videoDto.getDescription());
		savedVideo.setTags(videoDto.getTags());
//		savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
		savedVideo.setVideoStatus(videoDto.getVideoStatus());

		// Save to Db
		videoRepository.save(savedVideo);
		return videoDto;
	}

	public String uploadThumbnail(MultipartFile file, String videoId) {
		video savedVideo = getVideoById(videoId);

		String thumbnailUrl = s3Service.uploadFile(file);

		savedVideo.setThumbnailUrl(thumbnailUrl);

		videoRepository.save(savedVideo);
		return thumbnailUrl;
	}

	public videoDto getVideoDetails(String videoId) {
		video savedVideo = getVideoById(videoId);
		
		videoDto videoDto = new videoDto();
		videoDto.setDescription(savedVideo.getDescription());
		videoDto.setId(videoId);
		videoDto.setTags(savedVideo.getTags());
		videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
		videoDto.setVideoUrl(savedVideo.getVideoUrl());
		videoDto.setVideoStatus(savedVideo.getVideoStatus());
		videoDto.setTitle(savedVideo.getTitle());
		
		return videoDto;
	}
}
