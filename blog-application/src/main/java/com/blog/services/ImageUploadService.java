package com.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

	String uploadImage(String path, MultipartFile file) throws IOException;

	InputStream getImage(String path, String fileName) throws FileNotFoundException;

}
