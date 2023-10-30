package tabiMax.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUpload {
	String uploadFile(MultipartFile file) throws IOException;
}
