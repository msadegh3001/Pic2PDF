package ir.takdev.Pic2pdf.viewmodel;



import ir.takdev.Pic2pdf.model.PDF;
import ir.takdev.Pic2pdf.repository.AppRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.objectbox.reactive.DataSubscription;

public class PDFActivityViewModel extends BaseViewModel {

    private MutableLiveData<PDF> mPDFLiveData;
    public PDFActivityViewModel() {
        mPDFLiveData = new MutableLiveData<>();
    }

    public void loadPDF(long id) {
        DataSubscription sub = AppRepository.subscribeToPDF(this::refreshPDF, id, false);
        addSubscription(sub);
        AppRepository.refreshPDF(id);

    }

    private void refreshPDF(PDF pdf) {
        mPDFLiveData.postValue(pdf);
    }

    public LiveData<PDF> getPDF() {
        return mPDFLiveData;
    }
}
