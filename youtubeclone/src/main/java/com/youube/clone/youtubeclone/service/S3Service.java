package com.youube.clone.youtubeclone.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {

//	private final AmazonS3Client awsS3Client;
	private final AmazonS3 awsS3Client;
	public static final String BUCKET_NAME = "shinyenyi-youtube-clone";

//	public S3Service(AmazonS3Client awsS3Client) {
//		this.awsS3Client = awsS3Client;
//	}

	@Override
	public String uploadFile(MultipartFile file) {

		// prepare unique key
		var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		var key = UUID.randomUUID().toString() + "." + filenameExtension;

		var metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());

		// upload file to S3
		try {
			awsS3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"An exception occured while uploading file");
		}

		awsS3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);

//		return awsS3Client.getResourceUrl(BUCKET_NAME, key);
		return awsS3Client.getUrl(BUCKET_NAME, key).toString();

	}
}
