package ir.takdev.Pic2pdf.repository.response;

/**
 * Created by Pierce Zaifman on 2017-09-27.
 */

public class PDFUpdateResponse {

    private int mStatus;
    private String mErrorMessage;

    public PDFUpdateResponse(@Response.Status int status) {
        mStatus = status;
    }

    public void setErrorMessage(String message) {
        mErrorMessage = message;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    @Response.Status
    public int getStatus() {
        return mStatus;
    }

}
