package notes_arch;
// NOT COMPLETE works individually
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by hassaan on 2/26/2016.
 */
public class client {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        String name,note, filename;
        notes n = new notes();

        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("localhost", 5500);
        Scanner sc1 = new Scanner(s.getInputStream());
        //while(1==1)
        //{
        int option;
        System.out.print("Enter option. \n1 for storing\n2 for retrieving");
        option = sc.nextInt();

        switch(option){
            case 1 :
                //Statements
                PrintStream protocol = new PrintStream(s.getOutputStream());
                Scanner stringscan = new Scanner(System.in);

                protocol.print(100);
            {
                System.out.println("Enter name:");
                name = stringscan.next();

                System.out.println("Enter note:");
                note = stringscan.next();

                n.name = name;
                n.note = note;

                try
                {
                    //FileOutputStream fileout = new FileOutputStream("/tmp/employee.ser");
                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    out.writeObject(n);
                    out.close();

                    //fileout.close();
                }
                catch(IOException i)
                {
                    i.printStackTrace();
                }
            }
                break; //optional

            case 2 :
                //Statements
                PrintStream protocol2 = new PrintStream(s.getOutputStream());
                protocol2.print(200);
                System.out.println("Enter username for retrieval of the file:");
                filename = sc.next();
                protocol2.println(filename);

                {
                    ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                    notes objectReceived = (notes) in.readObject();
                    in.close();


                    System.out.println("\nNOTE=>");
                    System.out.println("\nName:"+ objectReceived.name);
                    System.out.println("\nNote:"+ objectReceived.note);

                    FileOutputStream fileout = new FileOutputStream(objectReceived.name+".txt",true);
                    ObjectOutputStream obj = new ObjectOutputStream(fileout);
                    obj.writeObject(objectReceived.note);
                    obj.close();
                    fileout.close();
                }

                break; //optional
            //You can have any number of case statements.
            default : //Optional
                //Statements
                System.out.println("default");
        }



            /*PrintStream p = new PrintStream(s.getOutputStream());
            p.println(number);
            temp = sc1.nextInt();
            System.out.println(temp);
            if(number==4)break;*/
        //}

    }
}
