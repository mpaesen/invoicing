package output;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Business;
import model.Quote;
import model.QuoteDetail;
import persistency.DBConnection;
import persistency.controller.CustomerController;
import persistency.controller.ProductController;
import persistency.controller.QuoteController;
import persistency.controller.QuoteDetailController;
import persistency.logging.BaseLogger;
import utilities.Constants;
import utilities.CreateDirectory;
import utilities.Figures;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

public class QuoteOutput extends DocumentOutput {
    /**
     * The resulting PDF file.
     */
    private static final String TITLE = "Offerte";
    private static final String PREFIX = "Offerte";
    private final Quote quote;
    private final Iterator<Business> details;

    public QuoteOutput(final String id) {
        this(QuoteController.getQuote(id));
    }

    public QuoteOutput(final Quote quote) {
        this.quote = quote;
        this.details = QuoteDetailController.readQuoteDetails(quote.getIdQuote()).iterator();
        // NO VAT on Quote
        setVat(false);
    }

    /**
     * Quote Header
     *
     * @return Table
     */
    public PdfPTable createQuoteHeader() {
        // a table with three columns
        final PdfPTable table = new PdfPTable(10);
        // the cell object
        PdfPCell cell = null;

        // row 4
        cell = new PdfPCell(new Phrase(Constants.DATE, FONT[18]));
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(quote.getQteCrtdate().getDatumInEuropeesFormaat(), FONT[20]));
        cell.setColspan(8);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        // row 5
        cell = new PdfPCell(new Phrase(Constants.REFERENCE_, FONT[18]));
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(quote.getQteHeaderComments(), FONT[20]));
        cell.setColspan(8);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        // row6 (blank line)
        cell = new PdfPCell(new Phrase(Constants.BLANK));
        cell.setColspan(10);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        return table;
    }

    /**
     * Round_Half_up added for linetotal
     *
     * @param table
     * @param lineCounter
     * @return
     */
    @Override
    public int createOneDetailLine(final PdfPTable table, int lineCounter) {
        PdfPCell cell;
        QuoteDetail detail;
        BigDecimal lineTotal;
        while (details.hasNext()) {
            detail = (QuoteDetail) details.next();

            cell = new PdfPCell(new Phrase((detail.getQteProdid() != null ? ProductController.getProduct(detail.getQteProdid()).getProdDesc() : detail.getQteComments()), FONT[23]));
            cell.setColspan(14);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(detail.getQteMeasure(), FONT[11]));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(((detail.getQteQty().doubleValue() != Figures.ZERO) ? detail.getQteQty().toString() : Constants.BLANK), FONT[11]));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(Constants.EMPTY, FONT[11]));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(((detail.getQtePrice().doubleValue() != Figures.ZERO) ? detail.getQtePrice().toString() + Constants.EURO : Constants.BLANK), FONT[11]));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            lineTotal = detail.getQtePrice().multiply(detail.getQteQty()).setScale(2, BigDecimal.ROUND_HALF_UP);
            setTotalExcl(getTotalExcl() + lineTotal.doubleValue());
            cell = new PdfPCell(new Phrase(((lineTotal.doubleValue() != Figures.ZERO) ? lineTotal.toString() + Constants.EURO : Constants.BLANK), FONT[18]));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            lineCounter--;
            if ((!detail.getQteComments().equals(Constants.EMPTY)) && (detail.getQteProdid() != null)) {
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
    public void lineComments(final QuoteDetail detail, final PdfPTable table) {
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("\t" + detail.getQteComments(), FONT[10]));
        cell.setColspan(22);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Constants.BLANK));
        cell.setColspan(3);
        table.addCell(cell);
    }

    public void createPdf(final String filename) throws IOException, DocumentException {
        // step 1
        final Document document = new Document(PageSize.A4);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(createHeader(TITLE, quote.getIdQuote()));
        document.add(createCustomerInfo(quote));
        document.add(createQuoteHeader());
        document.add(createDetails());
        document.add(createFooter());

        // step 5
        document.close();
    }

    public void run() {

        strManyDirectories.append(System.getProperty(Constants.DOCUMENT_ROOT)); // get
        // Document
        // Root
        strManyDirectories.append(DBConnection.getDocPath());
        strManyDirectories.append(Constants.QUOTE_DETAIL_PATH);
        try {
            CreateDirectory.run(strManyDirectories.toString());
            setCustomer(CustomerController.getCustomer(quote.getQteCusid()));
            createPdf(strManyDirectories + PREFIX + Constants.SEPARATOR_FLAT + quote.getIdQuote() + Constants.SEPARATOR_FLAT + getCustomer().getCusName() + Constants.EXTENTION);
        } catch (final IOException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        } catch (final DocumentException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        }
    }
}
