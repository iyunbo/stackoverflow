package org.iyunbo.encoding;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

class Html2Pdf {

	private static final Logger log = LoggerFactory.getLogger(Html2Pdf.class);

	void createPDF(final String content, final String filename) {
		// initiate a html document with a normal size
		final Document document = new Document(PageSize.LETTER);
		try {
			// the output file will be written in the target directory
			PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("target", filename)));
			// open the document after the writer init
			document.open();
			// turn the html string into input stream
			InputStream input = new ByteArrayInputStream(content.getBytes());
			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			// the core part: parsing html with Asian font
			worker.parseXHtml(writer, document, input, Charset.forName("UTF-8"), new AsianFontProvider());
		} catch (DocumentException | IOException e) {
			log.error("Failed to generate PDF for {}", filename, e);
		} finally {
			// never forget closing the document afterwards
			document.close();
		}
	}

	class AsianFontProvider extends XMLWorkerFontProvider {
		@Override
		public Font getFont(final String fontName, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color) {
			try {
				// the specific font for Chinese character
				BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				Font font = new Font(bf, size, style, color);
				font.setColor(color);
				return font;
			} catch (DocumentException | IOException e) {
				log.error("Failed to create font for {} with encoding {}", fontName, encoding, e);
				throw new IllegalArgumentException("incorrect font creation, please check the font parameters", e);
			}
		}
	}
}
