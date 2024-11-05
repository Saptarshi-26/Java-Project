package Library_management;
import java.util.*;
import java.io.*;
public class Open_control {

    //LIbrarycontrol lib= new LIbrarycontrol();

    static HashMap<String, member> member_id = new HashMap<>();// for storing members with id


    static ArrayList<books> book = new ArrayList<>();// for storing all books


    static ArrayList<String > idofprevious_members = new ArrayList<>();

    static String uid="0";



    static HashMap<books, Integer> record = new HashMap<>();//to store all books with their count

    public static void createFileIfNotExists(String filename) {
        File file = new File(filename);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + filename);
            } else {
                System.out.println("File already exists: " + filename);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }





    // Save all data to files
    static void saveData() {

    }

    // Load data from files
   // @SuppressWarnings("unchecked")



    static String nospace(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            ans += s.charAt(i) != ' ' ? s.charAt(i) : "";
        }
        return ans.toLowerCase();
    }

    static String idgernertae() {
        String s="";
        if(idofprevious_members.size()>0){
            s= idofprevious_members.get(0);
            idofprevious_members.remove(0);
        }
        else {
            String digit="";
            int n=1;
            int carry=0;
            for(int i=uid.length()-1;i>=0;i--){
                n=n+((int)uid.charAt(i)-48)+carry;
                String sum=Integer.toString(n);
                if(sum.length()>1){
                    carry=((int)sum.charAt(0))-48;
                    digit=digit+sum.substring(1);
                }
                else {
                    carry = 0;
                    digit = sum + digit;
                }
                n=0;
            }
            if(carry!=0)digit=Integer.toString(carry)+digit;

           s=s+digit;
           uid=s;
        }
        return s;
    }

    static void addbook() {
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            System.out.print("enter the name of your book ");
          //  sc.nextLine();
            s = (sc.nextLine());
            if (s.equals("null") || s.length() == 0)
                System.out.println("Book name cannot be null or empty ");
        }
        while (s.equals("null") || s.length() == 0);
        int i = 0;
        for (; i < book.size(); i++) {
            String listbookname=(book.get(i).title);
            listbookname =nospace(listbookname);
            if (listbookname.equals(nospace(s))) {
                break;
            }
        }
        if (i <book.size()) {
            record.put(book.get(i), record.get(book.get(i)) + 1);
            saveData();
        } else {
            System.out.println("enter the author ");
            String name = sc.nextLine();
            System.out.println("enter the price ");
            int price = sc.nextInt();

            System.out.println("enter the number of pages ");

            int pages = sc.nextInt();

            books b = new books(s, name, price, pages);
            book.add(b);
            record.put(b, 1);
        }
        saveData();
    }//done

    static void getbook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your id ");
        //sc.nextLine();
        String s = sc.next();
        if (!member_id.containsKey(s)) {
            System.out.println(" Invalid UID ");
            return;
        }
        String password =member_id.get(s).password;
        System.out.println("Enter your password ");
        String p= sc.next();
        if(!p.equalsIgnoreCase(password)){
            System.out.println("Wrong password ");
            return;
        }
        System.out.println(" enter the name of the book ");
        sc.nextLine();
        String name = nospace(sc.nextLine());
        int i = 0;
        for (; i < book.size(); i++) {
            String listbookname=(book.get(i).title);
                   listbookname =nospace(listbookname);
            if (listbookname.equals(name)) {
                //saveData();
                break;
            }
        }
        if (i == book.size() ) {
            System.out.println(" book not available ");
            return;
        }
        record.put(book.get(i), record.get(book.get(i)) - 1);
        member_id.get(s).arrayList_books.add(book.get(i));
        if (record.get(book.get(i)) == 0) book.remove(book.get(i));
        saveData();
    }//done

    static void add_member() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your name ");
        String name = sc.nextLine();
        String id = idgernertae();
        System.out.println("your USER ID is " + id);
        System.out.println();
        System.out.println(" Enter a password ");
        String password = sc.next();
        member m = new member(name, id,password);
        member_id.put(id, m);
        saveData();
        System.out.println(" you are successfully registered in the library ");
        saveData();
    }//done

    static void display_allmembers() {
        if(member_id.size()==0){
            System.out.println(" No members found ...");
            return ;
        }
        for (Map.Entry<String, member> entry : member_id.entrySet()) {
            System.out.println(" USER ID: " + entry.getKey() + ", NAME: " + entry.getValue().name);
        }

    }//done

    static void display_Allbooks() {
        if(book.size()==0){
            System.out.println("No books found ...");
            return ;
        }
        for (int i = 0; i < book.size(); i++) {
            System.out.println("TITLE :" + book.get(i).title + " AUTHOR :" + book.get(i).author + " price :" +book.get(i).price+ " Number of pages :" + book.get(i).pages);
        }
    }//done

    static void display_booksfromlist(books book) {
        System.out.println("TITLE " + book.title + " AUTHOR " + book.author + " price " + " Number of pages " + book.pages);
    }//done

    static void display_studentBookList() {
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println(" enter your id ");
        String s = sc.next();
        if (!member_id.containsKey(s)) {
            System.out.println("INVALID USER ID");
            return;
        }
        password=member_id.get(s).password;
        System.out.println(" Enter your password ");
        String p= sc.next();
        if(!p.equalsIgnoreCase(password)){
            System.out.println("Wrong password ");
            return;
        }
        if(member_id.get(s).arrayList_books.size()==0){
            System.out.println("No books found in your list ...");
            return ;
        }
        for (int i = 0; i < member_id.get(s).arrayList_books.size(); i++) {
            display_booksfromlist(member_id.get(s).arrayList_books.get(i));
            System.out.println();
        }
    }//done

    static void return_book() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your id ");
        String s = sc.next();
        if (!member_id.containsKey(s)) {
            System.out.println("INVALID USER ID ");
            return;
        }
        sc.nextLine();
        String password =member_id.get(s).password;
        System.out.println("Enter your password ");
        String p= sc.next();
        if(!p.equalsIgnoreCase(password)){
            System.out.println("Wrong password ");
            return;
        }
        System.out.print("enter your book name ");
        sc.nextLine();
        String name = nospace(sc.nextLine());
        int i = 0;
        books b = new books();
        for (; i < book.size(); i++) {
            String listbookname=(book.get(i).title);
            listbookname =nospace(listbookname);
            if (listbookname.equals(name))
                break;
        }
        int j = 0;
        for (; j < member_id.get(s).arrayList_books.size(); j++) {
            if (nospace(member_id.get(s).arrayList_books.get(j).title).equals(name)) {
                if (i == book.size()) {
                    book.add(member_id.get(s).arrayList_books.get(j));

                }
                b=member_id.get(s).arrayList_books.get(j);
                break;
            }
        }
        if(j==member_id.get(s).arrayList_books.size()){
            System.out.println("Enter you dont have this book ");
            return ;
        }
        if (record.containsKey(member_id.get(s).arrayList_books.get(j))) {
            record.put(member_id.get(s).arrayList_books.get(j), record.get(member_id.get(s).arrayList_books.get(j)) + 1);
        }
        member_id.get(s).arrayList_books.remove(b);
        saveData();
    }

    static void delete_myaccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println(" enter your uid ");
        String s = sc.next();
        if (!member_id.containsKey(s)) {
            System.out.println("INVALID USER ID ");
            return;
        }
        idofprevious_members.add(s);
        sc.nextLine();
        String password =member_id.get(s).password;
        System.out.println("Enter your password ");
        String p= sc.next();
        if(!p.equalsIgnoreCase(password)){
            System.out.println("Wrong password ");
            return;
        }
      for(int x=0;x<member_id.get(s).arrayList_books.size();x++){
        String name = nospace(member_id.get(s).arrayList_books.get(x).title);
        int i = 0;
        books b = new books();
        for (; i < book.size(); i++) if (nospace(book.get(i).title).equals(name)) break;
        int j = 0;
        for (; j < member_id.get(s).arrayList_books.size(); j++) {
            if (nospace(member_id.get(s).arrayList_books.get(j).title).equals(name)) {
                if (i == book.size()) {
                    book.add(member_id.get(s).arrayList_books.get(j));

                }
                b=member_id.get(s).arrayList_books.get(j);
                break;
            }
        }
        if (record.containsKey(member_id.get(s).arrayList_books.get(j))) {
            record.put(member_id.get(s).arrayList_books.get(j), record.get(member_id.get(s).arrayList_books.get(j)) + 1);
        }

          member_id.get(s).arrayList_books.remove(b);
    }
      member_id.remove(s);
        saveData();

}

    static void delete_bookfromlibrary( String pasword){
        Scanner sc = new Scanner(System.in);
        System.out.println(" enter the authority password ");
        String s = sc.next();
        if(s.equalsIgnoreCase(pasword)){
            System.out.println(" enter the book name ");
            sc.nextLine();
            String name = sc.nextLine();
            books b = new books();
            int i;
            for( i=0;i<book.size();i++){
                String listbookname=(book.get(i).title);
                listbookname =nospace(listbookname);
                if(nospace(name).equals(listbookname)){
                   // b=book.get(i);
                    book.remove(i);
                    saveData();;
                    return;
                }
            }
            if(i==book.size()) {
                System.out.println("book does not exist ");
                return;
            }
            record.remove(b);
            System.out.println("book removed successfully");
            saveData();

        }

    }

    static void remove_allbooks(String password){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the authority password ");
        String s= sc.next();
        if(!s.equalsIgnoreCase(password)){
            System.out.println("Wrong password ");
            return;
        }

        book.clear();
        saveData();
        record.clear();
        saveData();
    }

    static void rm(){
        member_id.clear();
        saveData();
    }

    public static void main(String[] args) {

        String passcode ="8520";

        Scanner sc = new Scanner(System.in);
        System.out.println("New User -> press 1");
        System.out.println("Add book to Library --> 2");
        System.out.println("see all members list  --> 3 ");
        System.out.println("see all book list  --> 4 ");
        System.out.println("to get a book in your list  --> 5 ");
        System.out.println(" To check your book list --> 6");
        System.out.println("To return book to library -->7");
        System.out.println("To delete your account -->8 ");
        System.out.println("To delete a book from library -->9");
        System.out.println(" To delete all books from library -->10");
        System.out.println("press o to exit library ");
        //rm();
        int n = sc.nextInt();
        do {
            if (n == 1) {
                add_member();
            } else if (n == 2) addbook();
            else if (n == 3) display_allmembers();
            else if (n == 4) display_Allbooks();
            else if (n == 5) getbook();
            else if (n == 6) display_studentBookList();
            else if (n==7) return_book();
            else if(n==8)delete_myaccount();
            else if (n==9) delete_bookfromlibrary(passcode);
            else if (n==10) remove_allbooks(passcode);{

            }


            System.out.println();
            System.out.println();
            System.out.println(" enter the operation you want to perform ");
            n= sc.nextInt();
        }while(n>0);
    }
    }


