package beans;

public class Good {
    private int id;
    private String name;
    private String kind;
    private double price;
    private String origin;
    private String picture;


    public Good(int id, String name, String kind, double price, String origin,String picture) {

        this.id = id;
        this.name = name;
        this.kind = kind;
        this.price = price;
        this.picture = picture;
        this.origin = origin;
    }

    public Good() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getKind() {

        return kind;
    }

    public void setKind(String kind) {

        this.kind = kind;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPicture() {

        return picture;
    }

    public void setPicture(String picture) {

        this.picture = picture;
    }


    public int hashCode() {
        return this.getId()+this.getName().hashCode();
    }

    public boolean equals(Object obj) {
        if(this==obj)
        {
            return true;
        }
        if(obj instanceof Good)
        {
            Good i = (Good)obj;
            if(this.getId()==i.getId()&&this.getName().equals(i.getName()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "商品编号："+this.getId()+",商品名称："+this.getName();
    }
}
