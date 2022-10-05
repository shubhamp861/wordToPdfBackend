package com.example.demo;

import com.example.demo.controller.ImageUploadController;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileService {
    Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
    public File convertWordToPdf(MultipartFile file){
        File fileSample = new File("Sample.pdf");
        try {
            //test chanhes
        //  File f = multipartToFile(file);
//          BufferedReader r = new BufferedReader(new FileReader(f));
//          System.out.println( r.readLine());
            InputStream inputStream = file.getInputStream();
            logger.info("creating stream for multipart file");
           // FileInputStream templateInputStream=new FileInputStream(fileSample);
          //  WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(inputStream);
            fileSample.createNewFile();
            logger.info("creating a sample pdf file .....");
            FileOutputStream os = new FileOutputStream(fileSample);
            Docx4J.toPDF(wordMLPackage,os);
            os.flush();
            os.close();
            logger.info("file convert request completed.....");
        } catch (Throwable e) {e.printStackTrace();}
    return fileSample;
    }
//    public  File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
//        File convFile = new File("fil.docx");
//        convFile.createNewFile();
//        multipart.transferTo(convFile);
//        return convFile;}
}
