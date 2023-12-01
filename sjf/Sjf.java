import java.util.*;
public class Sjf{
    static ArrayList<String> pid=new ArrayList<>();
    static ArrayList<Integer> AT=new ArrayList<>();
    static ArrayList<Integer> BT=new ArrayList<>();
    static ArrayList<Integer> CT=new ArrayList<>();
    static ArrayList<Integer> TAT=new ArrayList<>();
    static ArrayList<Integer> WT=new ArrayList<>();
    static int nop;
    public void sortList(){
        for(int i=0; i<nop-1; i++){
            int min=i;
            for(int j=i+1; j<nop; j++){
                if(BT.get(min)>BT.get(j)){
                    min=j;
                }
            }
            int temp2=BT.get(min);
            BT.set(min,BT.get(i));
            BT.set(i,temp2);

            int temp=AT.get(min);
            AT.set(min,AT.get(i));
            AT.set(i,temp);

            String temp1=pid.get(min);
            pid.set(min,pid.get(i));
            pid.set(i,temp1);

            
        }
    }
    public void getCT(){
        CT.add(0,BT.get(0));
        for(int i=1; i<nop; i++){
            CT.add(i,(CT.get(i-1)+BT.get(i)));
        }
    }
    public void getTAT(){
        for(int i=0; i<nop; i++){
            TAT.add(i,(CT.get(i)-AT.get(i)));
        }
    }
    public void getWT(){
        for(int i=0; i<nop; i++){
            WT.add(i,(TAT.get(i)-BT.get(i)));
        }
    }
    public double AvgWT(){
        int twt=WT.get(0);
        for(int i=1; i<nop; i++){
            twt=twt+WT.get(i);
        }
        return (twt/nop);
    }
    public static void main(String[] args) {
   
        Sjf sj=new Sjf();
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the no. of process.");
        nop=sc.nextInt();
        for(int i=0; i<nop; i++){
            System.out.println("enter the pid of "+(i+1)+" process");
            pid.add(i,sc.next());
            System.out.println("enter the arival time of "+(i+1)+" process");
            AT.add(i,sc.nextInt());
            System.out.println("enter the Burst time of "+(i+1)+" process");
            BT.add(i,sc.nextInt());
        }
        sj.sortList();
        sj.getCT();
        sj.getTAT();
        sj.getWT();
        System.out.println("Gaint Chart.");
        System.out.println("pid "+"AT "+"BT "+"CT "+"TAT "+"WT");
        for(int i=0; i<nop; i++){
            System.out.println(pid.get(i)+"  "+AT.get(i)+"  "+BT.get(i)+"  "+CT.get(i)+
            "  "+TAT.get(i)+"  "+WT.get(i));
        }
        System.out.println("Avarage waiting time= "+sj.AvgWT());
    }
} 