package ir.takdev.Pic2pdf.viewmodel;

import ir.takdev.Pic2pdf.model.PDF;
import ir.takdev.Pic2pdf.repository.AppRepository;

public class NewpdfActivityViewModel extends BaseViewModel{
    public NewpdfActivityViewModel() {
    }
    public void addPDF(PDF pdf){
        AppRepository.addPDF(pdf);
    }
}
