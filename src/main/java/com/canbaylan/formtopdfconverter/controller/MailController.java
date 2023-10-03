package com.canbaylan.formtopdfconverter.controller;

import com.canbaylan.formtopdfconverter.utils.EmailUtils;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1")
@Slf4j
public class MailController {

    @Autowired
    EmailUtils emailUtils;

    @PostMapping
    public ResponseEntity<String> sendMail(){
        try {
            //emailUtils.sendSimpleMessage();
            String pdfFileName = "baylanguvenlik.pdf";

            ClassLoader classLoader = MailController.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(pdfFileName);
            String outputStream="src/main/resources/yeni-dokuman.pdf";
            String description="yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +
                    "yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni "
                    +"yeni eklenen yeni eklenen yeni " + "yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +
                    "yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +
                    "yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +
                    "yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +
                    "yeni eklenen yeni eklenen yeni " +"yeni eklenen yeni eklenen yeni " +
                    "eklenen metin yeni eklenen metin yeni eklenen metin yeni eklenen metin yeni eklenen metin";
            if (inputStream != null) {
                log.info("erisildi");

                PdfDocument pdfDocument = new PdfDocument(new PdfReader(inputStream), new PdfWriter(outputStream));
                int pageNumber = 1;
                PdfFont font = PdfFontFactory.createFont();
                int fontSize = 10;
                Document document = new Document(pdfDocument);
                document.setFont(font).setFontSize(fontSize);
                float x = 100;
                float y = 100;
                List<Integer> dataList = new ArrayList<>();
                dataList.add(144); dataList.add(628);dataList.add(420); dataList.add(626);dataList.add(110);
                dataList.add(606); dataList.add(90);dataList.add(584); dataList.add(420);dataList.add(582);
                dataList.add(420); dataList.add(560);dataList.add(180); dataList.add(523);dataList.add(180);
                dataList.add(508); dataList.add(180);dataList.add(493); dataList.add(180);dataList.add(479);
                dataList.add(180); dataList.add(464);dataList.add(365); dataList.add(664);dataList.add(470);
                dataList.add(664); dataList.add(43);dataList.add(50); dataList.add(450);dataList.add(50);
                dataList.add(450); dataList.add(50);dataList.add(70); dataList.add(434);

                for(int i=0;i<dataList.size();i+=2){
                    x= dataList.get(i);
                    y= dataList.get(i+1);
                    if(i == dataList.size()-2){
                        document.showTextAligned(new Paragraph(description).setWidth(450).setFixedLeading((float)21.7), x, y, pageNumber,
                                TextAlignment.LEFT, VerticalAlignment.TOP, 0);
                    }
                    else{
                        document.showTextAligned(new Paragraph("abc abc"), x, y, pageNumber,
                                TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
                    }
                }

                String imagePath = "x.png";
                Image image = new Image(ImageDataFactory.create(imagePath));
                image.setWidth(15);
                List<Integer> tableCheck = List.of(78,150,203,255);
                for(Integer num:tableCheck){
                    image.setFixedPosition(num,557);
                    document.add(image);
                }








                document.close();
            } else {
                System.out.println("PDF dosyasına erisilemedi.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        String responseJson = "{\"message\": \"Mail sent successfully!\"}";
        return ResponseEntity.ok(responseJson);
    }
}
/*
aciklama : document.showTextAligned(new Paragraph(description).setWidth(450).setFixedLeading((float)21.7), x, y, pageNumber,
                        TextAlignment.LEFT, VerticalAlignment.TOP, 0);

                        document.showTextAligned(new Paragraph("abc abc"), x, y, pageNumber,
                                    TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
 */
