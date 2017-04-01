package output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import model.Address;
import model.Business;
import model.Customer;
import model.Figures;
import model.Quote;
import model.QuoteDetail;
import persistency.RDBConnection;
import persistency.controller.AddressController;
import persistency.controller.CodeController;
import persistency.controller.CustomerController;
import persistency.controller.ProductController;
import persistency.controller.QuoteController;
import persistency.controller.QuoteDetailController;
import utilities.Constants;
import utilities.CreateDirectory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class QuoteOutput extends DocumentOutput {
	private Quote quote;

	private Iterator<Business> details;

	private Customer customer;

	/** The resulting PDF file. */
	private static final String TITLE = "Offerte";

	private static final String PREFIX = "Offerte";

	public QuoteOutput(String id) {
		this(QuoteController.getQuote(id));
	}

	public QuoteOutput(Quote quote) {
		this.quote = quote;
		this.details = QuoteDetailController.readQuoteDetails(
				quote.getIdQuote()).iterator();
		// NO VAT on Quote
		setVat(false);
	}

	/**
	 * Quote Header
	 * 
	 * @return Table
	 */
	@Override
	public PdfPTable createCustomerInfo() {
		// a table with three columns
		PdfPTable table = new PdfPTable(10);
		// the cell object
		PdfPCell cell = null;
		Address address = AddressController.getAddress(quote.getQteDlvAddid());
		// 10 cells per row
		// row 1
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(1);
		table.addCell(cell);

		if (!getHead().getCusMobile().equals("")) {
			cell = new PdfPCell(new Phrase("GSM: " + getHead().getCusMobile(),
					FONT[8]));
		} else {
			cell = new PdfPCell(new Phrase(Constants.BLANK));
		}
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(customer.getCusName(), FONT[17]));
		cell.setColspan(5);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 2
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(1);
		table.addCell(cell);

		if (!getHead().getCusVat().equals("")) {
			cell = new PdfPCell(new Phrase("BTW: " + getHead().getCusVat(),
					FONT[8]));
		} else {
			cell = new PdfPCell(new Phrase(Constants.BLANK));
		}
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		if (!getHead().getCusInfo().equals("")) {
			cell = new PdfPCell(new Phrase("Hr." + getHead().getCusInfo(),
					FONT[8]));
		} else {
			cell = new PdfPCell(new Phrase(Constants.BLANK));
		}
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(address.getAddStreet()
				+ Constants.BLANK
				+ address.getAddNumber()
				+ (address.getAddBox().equals("") ? "" : " bus "
						+ address.getAddBox()), FONT[17]));
		cell.setColspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 3
		cell = new PdfPCell(new Phrase(getHead().getCusName(), FONT[1]));
		cell.setColspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(address.getAddZip()
				+ Constants.BLANK
				+ address.getAddCity()
				+ Constants.PERIOD
				+ CodeController.getOneCodeDetail(Constants.COUNTRY_CODE,
						address.getAddCountry()).getCodeDesc(), FONT[17]));
		cell.setColspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 4
		cell = new PdfPCell(new Phrase("Datum", FONT[18]));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(quote.getQteCrtdate()
				.getDatumInEuropeesFormaat(), FONT[20]));
		cell.setColspan(8);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 5
		cell = new PdfPCell(new Phrase("Referentie", FONT[18]));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(quote.getQteHeaderComments(), FONT[20]));
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BTW, FONT[18]));
		cell.setColspan(1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(customer.getCusVat(), FONT[20]));
		cell.setColspan(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row6 (blank line)
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(10);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row7 (blank line)
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(10);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		return table;
	}

	/**
	 * @param table
	 * @param lineCounter
	 * @return
	 */
	@Override
	public int createOneDetailLine(PdfPTable table, int lineCounter) {
		PdfPCell cell;
		QuoteDetail detail;
		BigDecimal lineTotal;
		while (details.hasNext()) {
			detail = (QuoteDetail) details.next();

			cell = new PdfPCell(new Phrase(
					(detail.getQteProdid() != null) ? ProductController
							.getProduct(detail.getQteProdid()).getProdDesc()
							: detail.getQteComments(), FONT[Integer.parseInt(RDBConnection.getProps().getProperty(Constants.COMMENT_FONT_SIZE))]));
			cell.setColspan(14);
			table.addCell(cell);

//			cell = new PdfPCell(
//					new Phrase(
//							detail.getQteQty().doubleValue() != Figures.ZERO ? Constants.VOORLOPIG
//									: Constants.BLANK, FONT[11]));
//			cell.setColspan(2);
//			table.addCell(cell);

//			cell = new PdfPCell(new Phrase(detail.getQteMeasure(), FONT[11]));
//			table.addCell(cell);

			cell = new PdfPCell(
					new Phrase(
							(detail.getQteQty().doubleValue() != Figures.ZERO) ? detail
									.getQteQty().toString() : Constants.BLANK,
							FONT[11]));
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(detail.getQteMeasure(), FONT[11]));
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(Constants.EMPTY, FONT[11]));
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new PdfPCell(
					new Phrase(
							(detail.getQtePrice().doubleValue() != Figures.ZERO) ? detail
									.getQtePrice().toString() + Constants.EURO
									: Constants.BLANK, FONT[11]));
			cell.setColspan(3);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			lineTotal = detail.getQtePrice().multiply(detail.getQteQty())
					.setScale(2);
			setTotalExcl(getTotalExcl() + lineTotal.doubleValue());
			cell = new PdfPCell(
					new Phrase(
							(lineTotal.doubleValue() != Figures.ZERO) ? lineTotal
									.toString() + Constants.EURO
									: Constants.BLANK, FONT[18]));
			cell.setColspan(3);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			lineCounter--;

			if (!detail.getQteComments().equals(Constants.EMPTY)
					&& (detail.getQteProdid() != null)) {

				lineComments(detail, table);
				lineCounter--;
			}
		}
		return lineCounter;
	}

	/**
	 * Subcontractor not VAT obliged
	 * 
	 * @param table
	 */
	public void lineComments(QuoteDetail detail, PdfPTable table) {
		PdfPCell cell;
		cell = new PdfPCell(
				new Phrase("\t" + detail.getQteComments(), FONT[10]));
		cell.setColspan(22);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(3);
		table.addCell(cell);
	}

	public void createPdf(String filename) throws IOException,
			DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		// step 3
		document.open();
		// step 4
		document.add(createHeader(TITLE, quote.getIdQuote()));
		document.add(createCustomerInfo());
		document.add(createDetails());
		document.add(createFooter());

		// step 5
		document.close();
	}

	public void run() {
		String strManyDirectories = Constants.DOCUMENT_PATH
				+ Constants.QUOTE_DETAIL_PATH;
		try {
			CreateDirectory.run(strManyDirectories);
			customer = CustomerController.getCustomer(quote.getQteCusid());
			createPdf(strManyDirectories + PREFIX + Constants.SEPARATOR_FLAT
					+ quote.getIdQuote() + Constants.SEPARATOR_FLAT
					+ customer.getCusName() + Constants.EXTENTION);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
