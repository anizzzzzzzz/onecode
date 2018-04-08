package InstantLogin;

public class LoginFactory 
{
    public Login getLogin(String socialMedia)
    {
        if(socialMedia.equalsIgnoreCase("facebook"))
        {
            return new FBLogin();
        }
        else if(socialMedia.equalsIgnoreCase("hotmail"))
        {
            return new HotMailLogin();
        }
        else
        {
            return null;
        }
    }
}
