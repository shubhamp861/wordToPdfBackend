package com.example.demo.controller;
import com.example.demo.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class ImageUploadController {

	@Autowired
	FileService fileService;

	@PostMapping("/upload")
	@CrossOrigin
	public ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) {

		List<String> message;
		try {
			fileService.convertWordToPdf(file);
			message = Arrays.asList("Uploaded the file successfully: " + file.getName());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = Arrays.asList("Could not upload the file: " + file.getName());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

//	@GetMapping("/abc")
//	@CrossOrigin
//	public ResponseEntity<byte[]> get() throws Exception{
//		String  propertyFile = System.getProperty("user.dir")+"\\src\\main\\resources\\";
//		File file =  new File(propertyFile+"fil.docx");
//		InputStream in = new BufferedInputStream(new FileInputStream(file));
//		int u =(int) file.length();
//		//byte[] buf = new byte[u];
//
//		List<String> employees = Arrays.asList("hello","bye");
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(employees);
//		byte[] buf = json.getBytes();
//
//		String fileName = "sample.pdf";
//		HttpHeaders respHeaders = new HttpHeaders();
//		respHeaders.setContentLength(buf.length);
//		respHeaders.setContentType(new MediaType("text", "json"));
//		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//		System.out.println("called");
//		return new ResponseEntity<byte[]>(buf, respHeaders, HttpStatus.OK);
//
//	}
	@GetMapping("/a")
	@CrossOrigin
	public Object getpdf() throws Exception{
		String  propertyFile = System.getProperty("user.dir")+"\\src\\main\\resources\\";
		File file =  new File(propertyFile+"Sample.pdf");
		FileInputStream fileInputStream = new FileInputStream(file);
		return IOUtils.toByteArray(fileInputStream);
	}
	@GetMapping("/")
	@CrossOrigin
	public List<String> hello(){
		return Arrays.asList("Hello","Welcome");
	}
}