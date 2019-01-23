package ir.takdev.Pic2pdf.viewmodel;


import ir.takdev.Pic2pdf.model.PDF;
import ir.takdev.Pic2pdf.repository.AppRepository;
import ir.takdev.Pic2pdf.repository.response.PDFUpdateResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PDFFragmentViewModel extends BaseViewModel {

    private MutableLiveData<PDF> mPDFLiveData;
    private MutableLiveData<PDFUpdateResponse> mPDFResponseLiveData;

    public PDFFragmentViewModel() {
        mPDFLiveData = new MutableLiveData<>();
        mPDFResponseLiveData = new MutableLiveData<>();
    }

    public void loadPDF(long id) {
        AppRepository.subscribeToPDF(this::refreshPDF, id, true);
    }

    private void refreshPDF(PDF pdf) {
        mPDFLiveData.postValue(pdf);
    }

    public LiveData<PDF> getPDF() {
        return mPDFLiveData;
    }

    public LiveData<PDFUpdateResponse> getZooUpdateResponse() {
        return mPDFResponseLiveData;
    }

    public void updatePDF(PDF pdf) {
        AppRepository.updatePDF(pdf, mPDFResponseLiveData);
    }

    public void addPDF(String name,String size) {
        AppRepository.addPDF(new PDF(name,size), mPDFResponseLiveData);
    }
    public void deletePDF(long id){
        AppRepository.deletePDF(id);
    }
}
