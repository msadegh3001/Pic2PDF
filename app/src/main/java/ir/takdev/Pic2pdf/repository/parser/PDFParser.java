package ir.takdev.Pic2pdf.repository.parser;

import ir.takdev.Pic2pdf.model.Img;
import ir.takdev.Pic2pdf.model.PDF;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pierce Zaifman on 2017-09-28.
 */

public class PDFParser {

    private String mResponse;
    private PDF mZoo;
    private List<PDF> mZooList;
    private List<Img> mAnimalList;
    private Gson mGson;

    public PDFParser(String response) {
        mResponse = response;
        mGson = new Gson();
    }

    public void parsePDFList() {
        if (mResponse != null) {
            PDF[] pdfs = mGson.fromJson(mResponse, PDF[].class);
            mZooList = Arrays.asList(pdfs);
        }
    }

    public void parsePDF() {
        if (mResponse != null) {
            mZoo = mGson.fromJson(mResponse, PDF.class);
        }
    }

    public PDF getPDF() {
        return mZoo;
    }

    public List<PDF> getPDFList() {
        return mZooList;
    }

    public List<Img> getImgsList() {
        return mAnimalList;
    }
}
