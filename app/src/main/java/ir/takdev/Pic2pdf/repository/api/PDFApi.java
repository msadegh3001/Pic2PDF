package ir.takdev.Pic2pdf.repository.api;

import android.os.AsyncTask;

import ir.takdev.Pic2pdf.model.PDF;
import ir.takdev.Pic2pdf.repository.response.Response;

import androidx.lifecycle.Observer;

public class PDFApi {

    public static void addPDF(final PDF newPDF, Observer<Response> responseObserver) {
        // Fake network request
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            // Fake JSON response, it should come from the server
            String fakeResponse = "{\"name\": \"" + newPDF.getName() + "\"}";
            Response response = new Response(Response.STATUS_SUCCESS, fakeResponse);
            responseObserver.onChanged(response);
        });
    }

    public static void updatePDF(final PDF pdf, Observer<Response> responseObserver) {
        // Fake network request
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            // Fake JSON response, it should come from the server
            String fakeResponse = "{\"name\": \"" + pdf.getName() + "\", \"id\": \"" + pdf.getId() + "\"}";
            Response response = new Response(Response.STATUS_SUCCESS, fakeResponse);
            responseObserver.onChanged(response);
        });
    }

    public static void loadPDFs(Observer<Response> responseObserver) {
        //Fake network request
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            // Fake response, it should come from the server
            Response response = new Response(Response.STATUS_FAIL, "Error occurred!");
            responseObserver.onChanged(response);
        });
    }

    public static void loadPDF(long id, Observer<Response> responseObserver) {
        //Fake network request
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            // Fake response, it should come from the server
            Response response = new Response(Response.STATUS_FAIL, "Error occurred!");
            responseObserver.onChanged(response);
        });
    }
}
