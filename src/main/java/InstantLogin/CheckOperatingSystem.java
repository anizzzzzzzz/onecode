package InstantLogin;

public class CheckOperatingSystem {
    public String checkOS(){
        String os=System.getProperty("os.name");
        return os;
    }

    public static void main(String[] args) {
        System.out.println(new CheckOperatingSystem().checkOS());
    }
}
