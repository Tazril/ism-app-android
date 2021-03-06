package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model;

public class Member {
    public Boolean success;
    public String message;

    public Member(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "Member{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

}
