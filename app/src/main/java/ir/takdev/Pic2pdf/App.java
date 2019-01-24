package ir.takdev.Pic2pdf;

import android.app.Application;
import android.content.Context;

import ir.takdev.Pic2pdf.model.MyObjectBox;
import ir.takdev.Pic2pdf.model.PDF;
import java.util.ArrayList;
import java.util.List;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {
    private static App app;
    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
       initDataBase();
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }

    public static App getApp() {
        return app;
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    private void initDataBase() {
        try {
            Box<PDF> pdf=App.getBoxStore().boxFor(PDF.class);
            if(pdf.count()==0){
                List<PDF> pdfs = new ArrayList<PDF>();
                for (int i=1;i<3;i++){
                    pdfs.add(new PDF(""+i+" مگابایت", "مدارک "+i+""));
                }
                pdf.put(pdfs);
            }

        } catch (Exception ex) {

        }
    }
}
