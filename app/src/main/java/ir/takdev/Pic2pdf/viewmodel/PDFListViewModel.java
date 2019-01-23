package ir.takdev.Pic2pdf.viewmodel;

import ir.takdev.Pic2pdf.model.PDF;
import ir.takdev.Pic2pdf.repository.AppRepository;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.objectbox.reactive.DataSubscription;


public class PDFListViewModel extends BaseViewModel {

    private MutableLiveData<List<PDF>> mPDFsLiveData;

    public PDFListViewModel() {
        mPDFsLiveData = new MutableLiveData<>();
        DataSubscription subscription = AppRepository.subscribeToPDFList(this::refreshPDFs);
        addSubscription(subscription);
    }

    private void refreshPDFs(List<PDF> pdfs) {
        mPDFsLiveData.postValue(pdfs);
    }

    public LiveData<List<PDF>> getPDFs() {
        return mPDFsLiveData;
    }

    public void refreshPDFs() {
        AppRepository.refreshPDFs();
    }
}
