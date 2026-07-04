public interface IExportService {
    boolean exportInvoiceToTxt(Invoice invoice, String filePath);
    boolean exportStatisticToTxt(String filePath);
    boolean exportInventoryToTxt(String filePath);
}
