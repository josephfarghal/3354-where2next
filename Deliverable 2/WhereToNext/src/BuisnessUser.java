public class BuisnessUser extends User{
    boolean isBuisness = false;

    public BuisnessUser(String name, String password){
        super(name, password);
        this.isBuisness = true;
    }

    public locationInfo prompoteBuisness(String address){
        locationInfo buisness = new locationInfo(address);
        return buisness;
    }

    public locationInfo promoteBuisness(String address, String hyper){
        locationInfo buisness = new locationInfo(address, hyper);
        return buisness;
    }



}
