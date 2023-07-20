package attorneybrowsequestion;

public class BrowseRequestModel {

    private int attorneyid;

    public BrowseRequestModel(int attorneyid) {
        this.attorneyid = attorneyid;
    }

    public int getAttorneyid() {
        return attorneyid;
    }

    public void setAttorneyid(int attorneyid) {
        this.attorneyid = attorneyid;
    }
}
