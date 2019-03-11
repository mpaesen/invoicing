package output;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Business;
import model.Invoice;
import model.InvoiceDetail;
import persistency.DBConnection;
import persistency.controller.CustomerController;
import persistency.controller.InvoiceController;
import persistency.controller.InvoiceDetailController;
import persistency.controller.ProductController;
import persistency.logging.BaseLogger;
import utilities.Constants;
import utilities.CreateDirectory;
import utilities.Figures;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

/**
 * @author java
 *
 */
public class InvoiceOutput extends DocumentOutput {
    private final Invoice invoice;

    private final Iterator<Business> details;

    /**
     * The resulting PDF file.
     */
    private final String TITLE;

    private final String PREFIX;

    public InvoiceOutput(final String id) {
        this(InvoiceController.getInvoice(id));
    }

    public InvoiceOutput(final Invoice invoice) {
        this.invoice = invoice;
        this.details = InvoiceDetailController.readInvoiceDetails(invoice.getIdInvoice()).iterator();
        setVat(invoice.isInvVat());
        TITLE = (invoice.getInvType().equals(Constants.INVOICE_TYPE) ? Constants.INVOICE : Constants.CREDIT_NOTE);
        PREFIX = (invoice.getInvType().equals(Constants.INVOICE_TYPE) ? Constants.INVOICE : Constants.CREDIT_NOTE);
    }

    /**
     * Invoice Header
     *
     * @return Table
     */
    public PdfPTable createInvoiceHeader() {

        // a table with three columns
        final PdfPTable table = new PdfPTable(10);
        // the cell object
        PdfPCell cell = null;

        // row 5
        cell = new PdfPCell(new Phrase("Factuurdatum:", FONT[18]));
        cell.setColspan(3);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(invoice.getInvDate().getDatumInEuropeesFormaat(), FONT[20]));
        cell.setColspan(8);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        // row 6
        cell = new PdfPCell(new Phrase("Gelieve te betalen voor:", FONT[18]));
        cell.setColspan(3);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(invoice.getInvDueDate().getDatumInEuropeesFormaat(), FONT[20]));
        cell.setColspan(8);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        // row 7
        cell = new PdfPCell(new Phrase("Referentie:", FONT[18]));
        cell.setColspan(3);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(invoice.getInvHeaderComments(), FONT[20]));
        cell.setColspan(8);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        // row 8 (blank line)
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
        InvoiceDetail detail;
        BigDecimal lineTotal;
        while (details.hasNext()) {
            detail = (InvoiceDetail) details.next();

            cell = new PdfPCell(new Phrase((detail.getInvProdid() != null ? ProductController.getProduct(detail.getInvProdid()).getProdDesc() : detail.getInvComments()), FONT[23]));
            cell.setColspan(14);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(detail.getInvMeasure(), FONT[11]));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(((detail.getInvQty().doubleValue() != Figures.ZERO) ? detail.getInvQty().toString() : Constants.BLANK), FONT[11]));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(((!detail.getInvVat().equals(Constants._ZERO)) ? detail.getInvVat() + Constants.PERCENTAGE : Constants.BLANK), FONT[11]));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(((detail.getInvPrice().doubleValue() != Figures.ZERO) ? detail.getInvPrice().toString() + Constants.EURO : Constants.BLANK), FONT[11]));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            // calculate totals
            lineTotal = getInvoiceLineTotal(detail).setScale(2, BigDecimal.ROUND_HALF_UP);
            cell = new PdfPCell(new Phrase(((lineTotal.doubleValue() != Figures.ZERO) ? lineTotal.toString() + Constants.EURO : Constants.BLANK), FONT[18]));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            lineCounter--;
            if (!detail.getInvComments().equals(Constants.EMPTY) && (detail.getInvProdid() != null)) {
                lineComments(detail, table);
                lineCounter--;
            }
        }
        return lineCounter;
    }

    /**
     * @return BigDecimal
     */
    private BigDecimal getInvoiceLineTotal(final InvoiceDetail detail) {

        final BigDecimal total = detail.getInvQty().multiply(detail.getInvPrice());
        setTotalExcl(getTotalExcl() + total.doubleValue());
        if (isVat()) {
            final BigDecimal vat = new BigDecimal(detail.getInvVat());
            double vatAmount = Figures.ZERO;
            vatAmount = total.doubleValue() * vat.doubleValue() / Figures.HUNDRED;
            if (vat.doubleValue() == Figures.SIX) {
                setTotalVat6(getTotalVat6() + vatAmount);
            }
            if (vat.doubleValue() == Figures.TWENTYONE) {
                setTotalVat21(getTotalVat21() + vatAmount);
            }
        }
        return total;
    }

    /**
     * Subcontractor not VAT obliged
     *
     * @param table
     */
    public void lineComments(final InvoiceDetail detail, final PdfPTable table) {
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("\t" + detail.getInvComments(), FONT[10]));
        cell.setColspan(22);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Constants.BLANK));
        cell.setColspan(3);
        table.addCell(cell);
    }

    /**
     * @return table
     */
    public PdfPTable lineNoVat() {
        final PdfPTable table = new PdfPTable(25);
        if (!isVat()) {
            // subcontractor
            noVatContractor(table);
        }

        return table;

    }

    /**
     * Purpose: print CN VAT deduction text
     *
     * @return
     */
    public PdfPTable lineCNVat() {
        final PdfPTable table = new PdfPTable(25);
        if (invoice.getInvType().equals(Constants.CREDITNOTE_TYPE)) {
            vatCNDeduction(table);
        }
        return table;
    }


    public void createPdf(final String filename) throws IOException, DocumentException {
        // step 1
        final Document document = new Document(PageSize.A4);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(createHeader(TITLE, invoice.getIdInvoice()));
        document.add(createCustomerInfo(invoice));
        document.add(createInvoiceHeader());
        document.add(createDetails());
        // no VAT text
        document.add(lineNoVat());
        //CN VAT deduction
        document.add(lineCNVat());
        document.add(createFooter());

        // step 5
        document.close();
    }

    public void run() {
        strManyDirectories.append(System.getProperty(Constants.DOCUMENT_ROOT)); // get
        // Document
        // Root
        strManyDirectories.append(DBConnection.getDocPath());
        strManyDirectories.append(Constants.INVOICE_DETAIL_PATH);
        try {
            CreateDirectory.run(strManyDirectories.toString());
            setCustomer(CustomerController.getCustomer(invoice.getInvCusid()));
            createPdf(strManyDirectories + PREFIX + Constants.SEPARATOR_FLAT + invoice.getIdInvoice() + Constants.SEPARATOR_FLAT + getCustomer().getCusName() + Constants.EXTENTION);
        } catch (final IOException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        } catch (final DocumentException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        }
    }

}
