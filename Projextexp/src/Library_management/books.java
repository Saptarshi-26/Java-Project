package Library_management;
import java.io.Serializable;
import java.util.*;
public class books implements Serializable {
    private static final long serialVersionUID = 1L;
    String title;
    String author;
    int price;
    int pages;
    books(String title, String author, int price,int pages){
        this.title=title;
        this.author=author;
        this.price=price;
        this.pages=pages;
    }
   books(){
   }
}
