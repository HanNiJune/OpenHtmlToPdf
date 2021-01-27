package com.example.openhtmltopdf;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.*;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;

public class HtmlToPdf {
	public static void main(String[] args) {
	     try {
	      // Source HTML file
	      String inputHTML = "MyPage.html";
	      // Generated PDF file name
	      String outputPdf = "Output.pdf";
	      htmlToPdf(inputHTML, outputPdf);	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
		
	  private static Document html5ParseDocument(String inputHTML) throws IOException{
	    org.jsoup.nodes.Document doc;
	    System.out.println("parsing ...");
	    doc = Jsoup.parse(new File(inputHTML), "UTF-8");
	    System.out.println("parsing done ..." + doc);
	    return new W3CDom().fromJsoup(doc);
	  }
		
	  private static void htmlToPdf(String inputHTML, String outputPdf) throws IOException {
	    Document doc = html5ParseDocument(inputHTML);
	    File html = new File(inputHTML);
		String baseUri = FileUtils.readFileToString(html, StandardCharsets.UTF_8);
	  
	    OutputStream os = new FileOutputStream(outputPdf);
	    PdfRendererBuilder builder = new PdfRendererBuilder();
	    builder.withUri(outputPdf);
	    builder.toStream(os);
	    // using absolute path here
	    builder.useFont(new File("Gabriola.ttf"), 
	    "Gabriola");
	    builder.withW3cDocument(doc, baseUri);
	    builder.useUriResolver(new MyResolver());
	    builder.run();
	    System.out.println("PDF generation completed");
	    os.close();
	  }
}
