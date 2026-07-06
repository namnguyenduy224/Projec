import java.util.List;

public interface IInvoiceService extends IBaseService<Invoice> {
    Invoice createInvoice(TeaSession session);
    double calculateTotal(Invoice invoice);
    boolean payInvoice(String invoiceId);
    List<Invoice> findUnpaidInvoices();
}
