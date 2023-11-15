public class User{
    private String name;
    private String password;
    private int age;

    public String getName(){return this.name;}

    public String requestPass(){return this.password;}

    public User(String user, String pass){
        this.name = user;
        this.password = pass;
    }

    public User(String user, String pass, int days){
        this.name = user;
        this.password = pass;
        this.age = days;
    }

    public String toString(User x){return (this.name + " " + this.password + " ");}
}
