package ir.takdev.Pic2pdf.repository.database;

import ir.takdev.Pic2pdf.App;
import ir.takdev.Pic2pdf.model.PDF;

import java.util.Collection;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscription;

public class PDFDao {

    private static Box<PDF> getPDFBox() {
        BoxStore boxStore = App.getBoxStore();
        return boxStore.boxFor(PDF.class);
    }

    public static DataSubscription subscribeToPDFList(DataObserver<List<PDF>> observer) {
        return getPDFBox().query().build().subscribe().on(AndroidScheduler.mainThread()).observer(observer);
    }

    public static DataSubscription subscribeToPDF(DataObserver<PDF> observer, long id, boolean singleUpdate) {
//        SubscriptionBuilder<PDF> builder = getPDFBox().query().eager(Zoo_.animals).equal(Zoo_.id, id).build().subscribe().transform(list -> {
//            if (list.size() == 0) {
//                return null;
//            } else {
//                return list.get(0);
//            }
//        }).on(AndroidScheduler.mainThread());
//
//        if (singleUpdate) {
//            builder.single();
//        }
//        return builder.observer(observer);
        return  null;
    }

    public static void insertPDF(PDF pdf) {
        getPDFBox().put(pdf);
    }

    public static void insertPDFs(Collection<PDF> pdfs) {
        getPDFBox().put(pdfs);
    }
    public static void deletePDF(long id){
      getPDFBox().remove(id);
    // getPDFBox().removeAll();
    }


}