package Library_management;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
public class member implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    String id;
    String password;
    ArrayList<books> arrayList_books = new ArrayList<>();
    member(String name , String id,String password){
        this.name=name;
        this.id =id;
        this.password=password;
    }
    public ArrayList<books> getbook(){
        return arrayList_books;
    }
    public void update_listbooks(ArrayList<books> a){
        arrayList_books.clear();
        arrayList_books.addAll(a);
    }
     member(){
    }
}
