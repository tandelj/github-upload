package secure;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import secure.Hash.*;
import secure.OneTimeKey.*;

public class client {
            public static void main(String[] args) throws Exception
            {
                                    Socket s=new Socket("127.0.0.1",7777);
                                    if(s.isConnected())
                                    {
                                                System.out.println("Connected to server");
                                    }
                                    System.out.println("Enter size of array:");
                                    Scanner scanner=new Scanner(System.in);
                                    int n=scanner.nextInt();
                                    int a[]=new int[n];
                                    System.out.println("Enter element to array:");
                                    DataOutputStream out=new DataOutputStream(s.getOutputStream());
                                    out.writeInt(n);
                                    for(int i=0;i<n;i++)
                                    {
                                                int r =scanner.nextInt();
                                                out.writeInt(r);
                                    }
                                    System.out.println("Data Sent");
                                    DataInputStream din=new DataInputStream(s.getInputStream());
                                    int r;
                                    System.out.println("Receiving Sorted Data....");
                                    for(int i=0;i<n;i++)
                                    {
                                                r=din.readInt();
                                                System.out.print(r+" ");
                                    }
                                    s.close();
            }
} 

