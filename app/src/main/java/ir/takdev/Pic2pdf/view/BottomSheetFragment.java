package ir.takdev.Pic2pdf.view;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import ir.takdev.Pic2pdf.Actions;
import ir.takdev.Pic2pdf.MyDialogInterface;
import ir.takdev.Pic2pdf.R;
import ir.takdev.Pic2pdf.viewmodel.PDFFragmentViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;


public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    MyDialogInterface dialogInterface;
    LinearLayoutCompat openPDF, deletePDF, editPDF;
    View rootView;
    PDFFragmentViewModel mViewModel;
    private long id;

    public BottomSheetFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        id=getArguments().getLong("pdf_id");
        bindViews();
        mViewModel = ViewModelProviders.of(this).get(PDFFragmentViewModel.class);
        // Inflate the layout for this fragment
        return rootView;

    }
    public void setAction(MyDialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }

    private void bindViews() {
        openPDF=rootView.findViewById(R.id.openpdf);
        deletePDF=rootView.findViewById(R.id.removepdf);
        editPDF=rootView.findViewById(R.id.editpdf);
        openPDF.setOnClickListener(this);
        editPDF.setOnClickListener(this);
        deletePDF.setOnClickListener(this);
    }
    public void deletePDF(){
        try {
            mViewModel.deletePDF(id);
        }
        catch (Exception ex){

        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case  R.id.openpdf:
                dialogInterface.action(Actions. View);
                break;
            case R.id.editpdf:
                dialogInterface.action(Actions. Edit);
                break;
            case R.id.removepdf:
                dialogInterface.action(Actions. Delete);
                break;
        }
        dismiss();
    }

}
