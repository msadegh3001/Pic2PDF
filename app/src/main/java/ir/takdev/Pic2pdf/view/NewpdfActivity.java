package ir.takdev.Pic2pdf.view;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.hardware.camera2.*;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.takdev.Pic2pdf.R;
import ir.takdev.Pic2pdf.model.PDF;
import ir.takdev.Pic2pdf.viewmodel.NewpdfActivityViewModel;

public class NewpdfActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView imagesRecycler;
    private NewpdfActivityViewModel newpdfActivityViewModel;
    private AppCompatEditText pdfName,pdfSize,errorMsg;
    private AppCompatButton addButton,addImg;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpdf);
        bindView();
        initRecyclerView();
        newpdfActivityViewModel= ViewModelProviders.of(this).get(NewpdfActivityViewModel.class);
//        Toast.makeText(this,"فرم PDF جدید",Toast.LENGTH_LONG).show();
//        rcv_images=findViewById(R.id.rv_images);
    }
    private void initRecyclerView(){
        imagesRecycler=findViewById(R.id.imges_list);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this, 4);
        imagesRecycler.setLayoutManager(gridLayoutManager);
    }
    private void bindView() {
        try {
            pdfName=findViewById(R.id.pdf_name);
            pdfSize=findViewById(R.id.pdf_size);
            addButton=findViewById(R.id.add_pdf);
            addImg=findViewById(R.id.add_image);
            errorMsg=findViewById(R.id.error_msg);
            addButton.setOnClickListener(this);
            addImg.setOnClickListener(this);

        } catch (Exception ex) {
        }
    }

    @Override
    public void onClick(View v) {
        try{
            int id=v.getId();
            switch (id){
                case R.id.add_pdf:
                    PDF pdf=new PDF(pdfSize.getText().toString(),pdfName.getText().toString());
                    newpdfActivityViewModel.addPDF(pdf);
                    break;
                case R.id.add_image:
//                    Intent intent=new Intent(this,CameraActivity.class);
//                    startActivity(intent);
                    dispatchTakePictureIntent();
                    break;
            }

            // Toast.makeText(this,"فرم PDF جدید",Toast.LENGTH_LONG).show();
            finish();
        }
        catch (Exception ex) {
           errorMsg.setText(ex.getMessage());
        }
    }
    public  CameraManager getCameraInstance(){
        CameraManager c = null;
        try {
            // attempt to get a Camera instance

        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
    private void dispatchTakePictureIntent() {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
