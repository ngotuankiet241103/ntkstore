package tabiMax.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;
import tabiMax.service.FileUpload;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload{
	@Autowired
	private Cloudinary cloudinary;
	@Override
	public String uploadFile(MultipartFile file) throws IOException {
		
		return cloudinary.uploader()
				.upload(file.getBytes(), Map.of("public_id",UUID.randomUUID().toString()))
				.get("url")
				.toString();
				
	}

}
