package com.youube.clone.youtubeclone.controller;

import org.springframework.http.HttpStatus;

import com.youube.clone.youtubeclone.dto.uploadVideoResponse;
import com.youube.clone.youtubeclone.dto.videoDto;
import com.youube.clone.youtubeclone.service.videoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class videoController {

	private final videoService videoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public uploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file) {

		return videoService.uploadVideo(file);
	}

	@PostMapping("/thumbnail")
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId) {

		return videoService.uploadThumbnail(file, videoId);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public videoDto editVideoMetadata(@RequestBody videoDto videoDto) {
		return videoService.editVideo(videoDto);
	}

	@GetMapping("/{videoId}")
	@ResponseStatus(HttpStatus.OK)
	public videoDto getVideoDetails(@PathVariable String videoId) {
		return videoService.getVideoDetails(videoId);
	}
}
