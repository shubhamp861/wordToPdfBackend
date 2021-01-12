package com.example.demo;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {
    public File convertWordToPdf(MultipartFile file){

      try {
            FileInputStream templateInputStream=new FileInputStream(multipartToFile(file));
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            String outputfilepath ="/app/Sample.pdf";
            FileOutputStream os = new FileOutputStream(outputfilepath);
            Docx4J.toPDF(wordMLPackage,os);
            os.flush();
            os.close();
            System.out.println("complete");
        } catch (Throwable e) {e.printStackTrace();}
    return new File("/app/Sample.pdf");
    }
    public  File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File("/app/fil.docx");
        multipart.transferTo(convFile);
        return convFile;
    }
}
