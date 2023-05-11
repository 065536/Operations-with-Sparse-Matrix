import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class AddSpareMatrix{
    public static class Node{
        int location;
        int value;
        Node next;

        Node(int d1, int d2){
            location = d1;
            value = d2;
        }
    }

    public static List<Node> add(List<Node> m1, List<Node> m2){
        List<Node> M1 = m1;
        List<Node> M2 = m2;
        List<Node> result = new ArrayList<Node>();
        int row = M1.size();
        int c1= 0; 
        int c2 = 0;
        int e1 = 0; 
        int e2 = 0;
        int e = 0;
        for (int i = 0; i < row; i++){
            Node r1 = M1.get(i); 
            Node r2 = M2.get(i); 
            Node node = new Node(i+1,0); 
            Node tail = node;
            if (r1.next == null| r2.next == null){    
                
                if (r1.next == null & r2.next == null){ 
                    tail.next = null;
                }
                else{
                    if (r1.next == null){
                        tail.next = r2.next;
                    }
                    else{
                        tail.next = r1.next;
                    }
                }
            }
            
            else{ 
                r1 = r1.next;
                r2 = r2.next;
                
                while(r1 != null | r2 != null){
                    if (r1 != null){
                        Node r1Next = r1;
                        if (r2 != null){
                            
                            Node r2Next = r2;
                            c1 = r1Next.location;
                            c2 = r2Next.location;
                            
                            if(c1 < c2){
                                tail.next = r1Next;
                                tail = r1Next;
                                r1 = r1.next;
                            }
                            
                            else if(c1 > c2){
                                tail.next = r2Next;
                                tail = r2Next;
                                r2 = r2.next;
                            }
                            
                            else{
                                e1 = r1Next.value;
                                e2 = r2Next.value;
                                e = e1 + e2;
                                Node newNode = new Node(r1Next.location, e);
                                tail.next = newNode;
                                tail = newNode;
                                r1 = r1.next;
                                r2 = r2.next;
                            }
                        }
                        
                        else{
                            tail.next = r1Next;
                            tail = r1Next;
                            r1 = r1Next.next;
                        }
                    }
                    
                    else{
                        tail.next = r2;
                        r2 = r2.next;
                    }
                }
            }
            result.add(node);
        }
        return result;
    }

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        List<Node> M1 = new ArrayList<Node>();
        List<Node> M2 = new ArrayList<Node>();
        String str = input.nextLine();
        String[] arr = str.split(", ");
        Integer row = Integer.parseInt(arr[0]);
        str = arr[1];
        Integer col = Integer.parseInt(str.split(" ")[0]);
        for (int i = 1; i <= row; i++){
            Node node = new Node(i,0);
            Node tail = node;
            str = input.nextLine();
            if (str == null) break;
            arr = str.split(" ");
            for (int j = 1; j < arr.length; j++){
                String[] s = arr[j].split(":");
                if(s.length != 0){
                    int n1 = Integer.parseInt(s[0]);
                    int n2 = Integer.parseInt(s[1]);
                    Node nodeNext = new Node(n1, n2);
                    tail.next = nodeNext;
                    tail = nodeNext;
                }
            }
            M1.add(node);
        }

        str = input.nextLine();
        for (int i = 1; i <= row; i++){
            Node node = new Node(i,0);
            Node tail = node;
            str = input.nextLine();
            if (str == null) break;
            arr = str.split(" ");
            for (int j = 1; j < arr.length; j++){
                String[] s = arr[j].split(":");
                if(s.length != 0){
                    int n1 = Integer.parseInt(s[0]);
                    int n2 = Integer.parseInt(s[1]);
                    Node nodeNext = new Node(n1, n2);
                    tail.next = nodeNext;
                    tail = nodeNext;
                }
            }
            M2.add(node);
        }
        List<Node> result = add(M1, M2);
        System.out.print(String.valueOf(row)+", "+String.valueOf(col)+" ");
        for (int i = 0; i < result.size(); i++){
            System.out.print("\n");
            System.out.print(String.valueOf(i+1)+" ");
            Node printNode = result.get(i);
            
            if (printNode.next == null){
                System.out.print(":");
            }
            else{
                while(printNode.next != null){
                    printNode = printNode.next;
                    System.out.print(String.valueOf(printNode.location)+":"+String.valueOf(printNode.value)+" ");
                    
                }
            }
        }
    }

}