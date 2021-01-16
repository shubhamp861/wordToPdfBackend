package com.example.demo;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileService {
    public File convertWordToPdf(MultipartFile file){
        File fileSample = new File("Sample.pdf");
        try {
        //  File f = multipartToFile(file);
//          BufferedReader r = new BufferedReader(new FileReader(f));
//          System.out.println( r.readLine());
            InputStream inputStream = file.getInputStream();
           // FileInputStream templateInputStream=new FileInputStream(fileSample);
          //  WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(inputStream);
            fileSample.createNewFile();
            FileOutputStream os = new FileOutputStream(fileSample);
            Docx4J.toPDF(wordMLPackage,os);
            os.flush();
            os.close();
        } catch (Throwable e) {e.printStackTrace();}
    return fileSample;
    }
    public  File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File("fil.docx");
        convFile.createNewFile();
        multipart.transferTo(convFile);
        return convFile;}
}
