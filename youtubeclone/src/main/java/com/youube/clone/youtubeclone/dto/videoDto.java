package com.youube.clone.youtubeclone.dto;

import java.util.Set;

import org.springframework.data.annotation.Id;

import com.youube.clone.youtubeclone.module.videoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class videoDto {

	@Id
	private String id;
	private String title;
	private String description;
	private Set<String> tags;
	private String videoUrl;
	private videoStatus videoStatus;
	private String thumbnailUrl;
}
