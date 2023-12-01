public class FirstFit {
    public void allocte(int[] block, int[] pReaquest, int[] check){
        for(int i=0; i<pReaquest.length; i++){
            for(int j=0; j<block.length; j++){
                if(block[j]>=pReaquest[i] && block[j]==check[j]){
                    block[j]=block[j]-pReaquest[i];
                    System.out.println("Process"+i+" allocated to block"+j);
                    break;
                }
            }
        }
        System.out.println("available memory spaces after allocation of process");
        for(int i=0; i<block.length; i++){
            System.out.print(" "+block[i]);
        }
    }
    public static void main(String[] args) {
        int[] block={100,500,200,450,600};
        int[] blockCopy={100,500,200,450,600};
        int[] pReaquest={212,417,112,426};

        FirstFit ft=new FirstFit();
        ft.allocte(block, pReaquest,blockCopy);
    }
}
