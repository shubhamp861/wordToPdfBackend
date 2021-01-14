package com.example.demo.controller;
import com.example.demo.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
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
	Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
	@Autowired
	FileService fileService;

	@PostMapping("/upload")
	@CrossOrigin
	public Object uploadFile(@RequestParam("file") MultipartFile file) {
	    logger.info("called upload");
		try {
            File file1 = fileService.convertWordToPdf(file);
            FileInputStream fileInputStream = new FileInputStream(file1);
            return IOUtils.toByteArray(fileInputStream);
		} catch (Exception e) {
		    return e.toString(); }
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
	@GetMapping("/downloadPdf")
	@CrossOrigin
	public Object getpdf() throws Exception{
		Resource resource = new ClassPathResource("Sample.pdf");
		File file = resource.getFile();
		FileInputStream fileInputStream = new FileInputStream(file);
		return IOUtils.toByteArray(fileInputStream);
	}
	@GetMapping("/")
	@CrossOrigin
	public List<String> hello(){
		return Arrays.asList("Hello","Welcome");
	}

//	@GetMapping("/b")
//	@CrossOrigin
//	public ResponseEntity<InputStreamResource> hel()
//	{
//
//		Resource resource = new ClassPathResource("Sample.pdf");
//		long r = 0;
//		InputStream is=null;
//
//		try {
//			is = resource.getInputStream();
//			r = resource.contentLength();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return ResponseEntity.ok().contentLength(r)
//				.contentType(MediaType.parseMediaType("application/pdf"))
//				.body(new InputStreamResource(is));
//
//	}
}


