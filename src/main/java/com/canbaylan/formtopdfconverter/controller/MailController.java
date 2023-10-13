package com.canbaylan.formtopdfconverter.controller;

import com.canbaylan.formtopdfconverter.model.ServiceModel;
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
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


@RestController
@RequestMapping("api/v1")
@Slf4j
public class MailController {

    @Autowired
    EmailUtils emailUtils;

    @PostMapping
    public ResponseEntity<String> sendMail(@RequestBody ServiceModel serviceModel){
        try {

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
                dataList.add(420); dataList.add(560);dataList.add(365); dataList.add(664);dataList.add(470);
                dataList.add(664);dataList.add(180); dataList.add(523);dataList.add(180);
                dataList.add(508); dataList.add(180);dataList.add(493); dataList.add(180);dataList.add(479);
                dataList.add(180); dataList.add(464); dataList.add(103);dataList.add(50);dataList.add(450); dataList.add(50);
                dataList.add(70); dataList.add(434);



                List<String> tableModel = new ArrayList<>();
                tableModel.add(serviceModel.getInput1());tableModel.add(serviceModel.getInput2());tableModel.add(serviceModel.getInput3());
                tableModel.add(serviceModel.getInput4());tableModel.add(serviceModel.getInput5());tableModel.add(serviceModel.getInput6());
                tableModel.add(serviceModel.getTarih());tableModel.add(serviceModel.getSeriNo());
                tableModel.add(serviceModel.getInputf1());tableModel.add(serviceModel.getInputf2());tableModel.add(serviceModel.getInputf3());
                tableModel.add(serviceModel.getInputf4());tableModel.add(serviceModel.getInputf5());tableModel.add(serviceModel.getInputf6());
                tableModel.add(serviceModel.getInputf7());tableModel.add(serviceModel.getInputf8());


                for(int i=0;i<dataList.size();i+=2){
                    x= dataList.get(i);
                    y= dataList.get(i+1);
                    if(i == dataList.size()-2){
                        document.showTextAligned(new Paragraph(description).setWidth(450).setFixedLeading((float)21.7), x, y, pageNumber,
                                TextAlignment.LEFT, VerticalAlignment.TOP, 0);
                    }
                    else{
                        String tableData = tableModel.get(i / 2);
                        document.showTextAligned(new Paragraph(tableData), x, y, pageNumber,
                                TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
                    }
                }

                List<String> tableCheckModel = new ArrayList<>();
                tableCheckModel.add(serviceModel.getCheckbox1());tableCheckModel.add(serviceModel.getCheckbox2());tableCheckModel.add(serviceModel.getCheckbox3());tableCheckModel.add(serviceModel.getCheckbox4());

                String imagePath = "x.png";
                Image image = new Image(ImageDataFactory.create(imagePath));
                image.setWidth(15);
                List<Integer> tableCheck = List.of(78,150,203,255);
                for(int i=0;i<tableCheckModel.size();i++)
                {
                    if(tableCheckModel.get(i).equals("true"))
                    {
                        image.setFixedPosition(tableCheck.get(i),557);
                        document.add(image);
                    }
                }

                String signature1 = serviceModel.getSignature1();
                String signature2 = serviceModel.getSignature2();
                saveSignatureAsImage(signature1, "signature1.png");
                saveSignatureAsImage(signature2, "signature2.png");

                String imageSignaturePath1 = "signature1.png";
                Image image1 = new Image(ImageDataFactory.create(imageSignaturePath1));
                image1.setWidth(50);
                List<Integer> tableSignature = List.of(170);
                for(Integer num:tableSignature){
                    image1.setFixedPosition(num,50);
                    document.add(image1);
                }
                String imageSignaturePath2 = "signature2.png";
                Image image2 = new Image(ImageDataFactory.create(imageSignaturePath2));
                image2.setWidth(50);
                List<Integer> tableSignature2 = List.of(390);
                for(Integer num:tableSignature2){
                    image2.setFixedPosition(num,50);
                    document.add(image2);
                }

               // emailUtils.sendPdfByEmail(document,serviceModel.getTarih(),serviceModel.getInput1());
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

    private void saveSignatureAsImage(String base64Data, String fileName) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data.split(",")[1]);
        try (OutputStream stream = new FileOutputStream("" + fileName)) {
            stream.write(decodedBytes);
        }
    }
}
/*
aciklama : document.showTextAligned(new Paragraph(description).setWidth(450).setFixedLeading((float)21.7), x, y, pageNumber,
                        TextAlignment.LEFT, VerticalAlignment.TOP, 0);

                        document.showTextAligned(new Paragraph("abc abc"), x, y, pageNumber,
                                    TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
 */
