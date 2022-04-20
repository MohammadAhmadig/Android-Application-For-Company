package ir.mag.secondapp.Model;


public class Contact
{
    private String name = "";
    private String image = "";
    private String parent = "";
    private String abadiCode = "";
    private String admin = "";

    public Contact (String name, String image, String parent, String abadiCode, String admin)
    {
        this.name = name;
        this.image = image;
        this.parent = parent;
        this.abadiCode = abadiCode;
        this.admin = admin;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getParent ()
    {
        return parent;
    }

    public void setParent (String parent)
    {
        this.parent = parent;
    }

    public String getAbadiCode ()
    {
        return abadiCode;
    }

    public void setAbadiCode (String abadiCode)
    {
        this.abadiCode = abadiCode;
    }

    public String getAdmin ()
    {
        return admin;
    }

    public void setAgmin (String abadiCode)
    {
        this.admin = admin;
    }

}
