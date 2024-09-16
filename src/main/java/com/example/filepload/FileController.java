package com.example.filepload;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
	@Value("${location}")
	String location;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file)
			throws IllegalStateException, IOException {

		if (file.isEmpty()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File Could not be empty");
		} 

		try {
			String filePath = location+""+File.separator+file.getOriginalFilename();
			File destination = new File(filePath);
			file.transferTo(destination);
			return ResponseEntity.ok("file uploaded successfully" + file.getOriginalFilename());
 		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file upload failed");
		}

	}

}
