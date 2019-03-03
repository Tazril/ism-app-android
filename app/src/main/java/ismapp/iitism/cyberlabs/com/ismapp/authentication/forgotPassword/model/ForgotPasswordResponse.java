package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.model;

public class ForgotPasswordResponse {


    private boolean success;

    public ForgotPasswordResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    private String message;


}
