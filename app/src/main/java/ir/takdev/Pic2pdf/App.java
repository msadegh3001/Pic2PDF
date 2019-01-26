package ir.takdev.Pic2pdf;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import ir.takdev.Pic2pdf.model.MyObjectBox;
import ir.takdev.Pic2pdf.model.PDF;
import java.util.ArrayList;
import java.util.List;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {
    SharedPreferences sharedpreferences;
    private SharedPreferences initbook;
    private static App app;
    private static BoxStore boxStore;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        context=getApplicationContext();

        sharedpreferences = getSharedPreferences("mypref",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("isfirst", "true");
        editor.commit();
        if (sharedpreferences.getString("isfirst", "")=="true") {
            initDataBase();}
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
            sharedpreferences = getSharedPreferences("mypref",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("isfirst", "false");
            editor.commit();
            Toast.makeText(App.context,sharedpreferences.getString("isfirst", ""),Toast.LENGTH_LONG).show();

            Box<PDF> pdf=App.getBoxStore().boxFor(PDF.class);
            if(pdf.count()==0){
                List<PDF> pdfs = new ArrayList<PDF>();
                for (int i=1;i<8;i++){
                    pdfs.add(new PDF(""+i+" مگابایت", "مدارک "+i+""));
                }
                pdf.put(pdfs);
            }
        } catch (Exception ex) { }
    }
}
