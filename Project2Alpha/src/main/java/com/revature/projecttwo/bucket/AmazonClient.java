package com.revature.projecttwo.bucket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonClient {

	private AmazonS3 s3client;

	// grabs information from application.properties
	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	public String uploadFile(MultipartFile multipartFile) {

		String fileUrl = "";
		String fileName = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			fileName = generateFileName(multipartFile);
			// fileUrl is what we return so we know how to get back to the picture that we
			// posted
			fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
			// calls another method to upload into the S3 bucket
			uploadFileTos3bucket(fileName, file);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public String deleteFileFromS3Bucket(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
		return "Successfully deleted";
	}

	// this annotation initializes as soon as the obj is instantiated. Only once
	@PostConstruct
	private void initializeAmazon() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

		this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	/*
	 * this method puts an file into our bucket. .withCannedAcl is setting the
	 * access to Public Read so everyone can view it
	 */
	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));

	}

	public String uploadFile3(String fileName, String filepath) {

		String fileUrl = "";
		try {
			// fileUrl is what we return so we know how to get back to the picture that we
			// posted
			File f = new File(filepath);
			fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
			// calls another method to upload into the S3 bucket
			s3client.putObject(new PutObjectRequest(bucketName, fileName, f).withCannedAcl(CannedAccessControlList.PublicRead));
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}
	
}