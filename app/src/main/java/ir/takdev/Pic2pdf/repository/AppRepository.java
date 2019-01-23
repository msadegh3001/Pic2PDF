package ir.takdev.Pic2pdf.repository;

import ir.takdev.Pic2pdf.model.PDF;
import ir.takdev.Pic2pdf.repository.api.PDFApi;
import ir.takdev.Pic2pdf.repository.database.PDFDao;
import ir.takdev.Pic2pdf.repository.parser.PDFParser;
import ir.takdev.Pic2pdf.repository.response.PDFUpdateResponse;
import ir.takdev.Pic2pdf.repository.response.Response;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscription;

public class AppRepository {

    public static DataSubscription subscribeToPDFList(DataObserver<List<PDF>> observer) {
        return PDFDao.subscribeToPDFList(observer);
    }

    public static DataSubscription subscribeToPDF(DataObserver<PDF> observer, long id, boolean singleUpdate) {
        return PDFDao.subscribeToPDF(observer, id, singleUpdate);
    }

    public static void refreshPDF(long id) {
        PDFApi.loadPDF(id, pdfResponse -> {
            if (pdfResponse != null && pdfResponse.getStatus() == Response.STATUS_SUCCESS) {
                PDFParser parser = new PDFParser(pdfResponse.getPayload());
                parser.parsePDF();
                PDF pdf = parser.getPDF();
                if (pdf != null) {
                    PDFDao.insertPDF(pdf);
                }
            }
        });
    }

    public static void refreshPDFs() {
        PDFApi.loadPDFs(pdfsResponse -> {
            if (pdfsResponse != null && pdfsResponse.getStatus() == Response.STATUS_SUCCESS) {
                PDFParser parser = new PDFParser(pdfsResponse.getPayload());
                parser.parsePDFList();
                List<PDF> pdfs = parser.getPDFList();
                if (pdfs != null) {
                    PDFDao.insertPDFs(pdfs);
                }
            }
        });
    }

    public static void addPDF(PDF newPDF, MutableLiveData<PDFUpdateResponse> liveResponse) {
        liveResponse.postValue(new PDFUpdateResponse(Response.STATUS_LOADING));
        PDFApi.addPDF(newPDF, pdfResponse -> handlePDFResponse(pdfResponse, liveResponse));
    }

    public static void updatePDF(PDF pdf, MutableLiveData<PDFUpdateResponse> liveResponse) {
        liveResponse.postValue(new PDFUpdateResponse(Response.STATUS_LOADING));
        PDFApi.updatePDF(pdf, pdfResponse -> handlePDFResponse(pdfResponse, liveResponse));
    }
    public static void deletePDF(long id){
        PDFDao.deletePDF(id);
    }
    private static void handlePDFResponse(Response pdfResponse, MutableLiveData<PDFUpdateResponse> liveResponse) {
        if (pdfResponse != null) {
            if (pdfResponse.getStatus() == Response.STATUS_SUCCESS) {
                PDFParser parser = new PDFParser(pdfResponse.getPayload());
                parser.parsePDF();
                PDF pdf = parser.getPDF();
                if (pdf != null) {
                    PDFDao.insertPDF(pdf);
                }
            }

            liveResponse.postValue(new PDFUpdateResponse(pdfResponse.getStatus()));
        }
    }

    public static void addPDF(PDF pdf) {
        PDFDao.insertPDF(pdf);
    }
}
