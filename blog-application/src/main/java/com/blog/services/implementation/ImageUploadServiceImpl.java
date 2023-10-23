package com.blog.services.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.ImageUploadService;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		//file Name
		String name=file.getOriginalFilename();
		
		//random name generation for file
		String randomId=UUID.randomUUID().toString();
		String randomName=randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//full path
		String filePath=path + File.separator +randomName;
		
		
		//create folder if not created 
		
		File f=new File(path);
		
		if(!f.exists())
		{
			f.mkdir();
		}
		
		
		//files copy
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return randomName;
	}

	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {

		String filePath=path + File.separator + fileName;
		InputStream inputStream=new FileInputStream(filePath);
		
		//db logic to return input stream
		return inputStream;
	}

}
