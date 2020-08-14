package com.atticus.hr.controllers;

import java.io.File;
import java.nio.file.Files;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atticus.hr.Exceptions.ErrorDetails;
import com.atticus.hr.Exceptions.FileUploadException;

@Controller
public class FileUploadController {
	
	@PostMapping("/uploadFile")
	public String handleFileUpload(@RequestParam("myFile") MultipartFile file) throws FileUploadException {
		if(!file.isEmpty()) {
			String name=file.getOriginalFilename();
			try {
				byte[] bytes=file.getBytes();
				Files.write(new File(name).toPath(),bytes);
			}
			catch(Exception e) {
				throw new FileUploadException("File could not be uploaded");
			}
		}
		return "redirect:/fileUpload";
	}
	@ExceptionHandler(FileUploadException.class)
	public String fileUploadFailure(FileUploadException e){
		ErrorDetails errorDetails=new ErrorDetails();
		errorDetails.setErrorMessage(e.getMessage());
		errorDetails.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return "uploaderror";
	}

}
