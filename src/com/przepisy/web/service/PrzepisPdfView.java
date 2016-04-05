package com.przepisy.web.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfWriter;
import com.przepisy.web.dao.Przepis;

public class PrzepisPdfView extends AbstractIText5PdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		document.add(new Chunk(""));

		Przepis przepis = (Przepis) model.get("przepis");
		response.setHeader("Content-Disposition", "inline; filename=\"" + przepis.getName() + ".pdf\"");
		response.setContentType("application/pdf");

		ClassPathResource cpr = new ClassPathResource("druk.pdf");
		PdfReader pdfTemplate = new PdfReader(cpr.getInputStream());

		PdfStamper stamper = new PdfStamper(pdfTemplate, response.getOutputStream());

		AcroFields form = stamper.getAcroFields();

		form.setField("name", przepis.getName());
		form.setField("skladniki", przepis.getSkladniki());
		form.setField("user", przepis.getUser().getLogin());
		form.setField("czas", Integer.toString(przepis.getCzas()));
		form.setField("opis", przepis.getText());
		form.setField("name", "AAAAA");

		PdfDictionary page = pdfTemplate.getPageN(1);
		PdfDictionary resources = page.getAsDict(PdfName.RESOURCES);
		PdfDictionary xobjects = resources.getAsDict(PdfName.XOBJECT);
		PdfName imgRef = xobjects.getKeys().iterator().next();
		PRStream stream = (PRStream) xobjects.getAsStream(imgRef);
		Image img = Image.getInstance(przepis.getPhoto());
		PdfImage image = new PdfImage(img, "", null);

		replaceStream(stream, image);
		stamper.close();
		pdfTemplate.close();

	}

	public static void replaceStream(PRStream orig, PdfStream stream) throws IOException {
		orig.clear();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		stream.writeContent(baos);
		orig.setData(baos.toByteArray(), false);
		for (PdfName name : stream.getKeys()) {
			orig.put(name, stream.get(name));
		}
	}

}