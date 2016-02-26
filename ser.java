# lab3package notes_arch;
// NOT COMPLETE works individually
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.util.*;
/**
 * Created by hassaan on 2/26/2016.
 */
public class server {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        notes n = new notes();
        Vector row = new Vector(1,1);
        String queryFile;
        ServerSocket s1 = new ServerSocket(5500);

        Socket ss = s1.accept();
       // while(1==1)
        //{

        int option;
        Scanner sc = new Scanner(ss.getInputStream());
        option = sc.nextInt();

        switch(option){
            case 100 :
                //Statements
            {
                ObjectInputStream in = new ObjectInputStream(ss.getInputStream());
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
            case 200 :
                //Statements
                Scanner sc1 = new Scanner(ss.getInputStream());
                queryFile = sc1.next();
                FileReader fileReader = new FileReader(queryFile+".txt");
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = null;
                String temp = " ";

                while((line = bufferedReader.readLine()) != null) {
                    temp += line;
                }

                n.name = queryFile;
                n.note = temp;

                try
                {
                    //FileOutputStream fileout = new FileOutputStream("/tmp/employee.ser");
                    ObjectOutputStream out = new ObjectOutputStream(ss.getOutputStream());
                    out.writeObject(n);
                    out.close();
                    out.flush();
                    //fileout.close();
                }
                catch(IOException i)
                {
                    i.printStackTrace();
                }

                break; //optional
            //You can have any number of case statements.
            default : //Optional
                //Statements
                System.out.println("Nothing!");
        }




/*            Scanner sc = new Scanner(ss.getInputStream());
            number = sc.nextInt();
            if (number==4) break;
            System.out.println(number);
            temp = number * 2;
            PrintStream p = new PrintStream(ss.getOutputStream());
            p.println(temp);
*/
        //}

    }
}
