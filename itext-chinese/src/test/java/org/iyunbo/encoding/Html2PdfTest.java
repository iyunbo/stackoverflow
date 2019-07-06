package org.iyunbo.encoding;

import org.junit.jupiter.api.Test;

class Html2PdfTest {

	private final Html2Pdf converter = new Html2Pdf();

	@Test
	void should_convert_chinese_html_to_pdf() {
		final String html = "<html>\n" +
				"<head>" +
				"</head>\n" +
				"<body>\n" +
				"chinese---快得利-协议重组贷款<br/>\n" +
				"</body>\n" +
				"</html>";
		converter.createPDF(html, "output.pdf");
	}
}