public class locationInfo {
    String address;
    String hyperlink;


    public locationInfo(){
        //Do nothing for now
    }

    public locationInfo(String adr){this.address = adr;}

    public locationInfo(String adr, String hyper){
        this.address = adr;
        this.hyperlink = hyper;
    }
}
