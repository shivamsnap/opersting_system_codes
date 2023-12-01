public class WorstFit {
    public void sort(int[] blocksort){
        for(int i=0; i<blocksort.length; i++){
            for(int j=0; j<blocksort.length; j++){
                if(blocksort[i]>blocksort[j]){
                    int temp=blocksort[i];
                    blocksort[i]=blocksort[j];
                    blocksort[j]=temp;
                }
            }
        }
    }
    public void allocte(int[] block, int[] pReaquest,int[] blocksort,int[] check){
        int[] copyOfSort=new int[blocksort.length];
        int checkp=0;
        int pno=0;
        for(int i=0; i<blocksort.length; i++){
            copyOfSort[i]=blocksort[i];
        }
        for(int i=0; i<pReaquest.length; i++){
            for(int j=0; j<blocksort.length; j++){
                if(blocksort[j]>=pReaquest[i] && blocksort[j]==copyOfSort[j]){
                    checkp=0;
                    for(int k=0; k<block.length; k++){
                        if (blocksort[j]==block[k] && block[k]==check[k]) {
                            block[k]=block[k]-pReaquest[i];
                            System.out.println("Process"+(+i+1)+" allocated to block"+k);
                            break;
                        }
                    }
                    blocksort[j]=blocksort[j]-pReaquest[i];
                   break;
                }else{
                    checkp++;
                    pno=i;
                }
            }
        }
        if(checkp!=0){
            System.out.println("Process"+(pno+1)+" not allocated due to insufficient block space");
        }
        System.out.println("available memory spaces after allocation of process");
        for(int i=0; i<block.length; i++){
            System.out.print(" "+block[i]);
        }
       
    }
    public static void main(String[] args) {
        WorstFit wf=new WorstFit();
        int[] block={100,500,200,450,600};
        int[] blockCopy={100,500,200,450,600};
        int[] blocksort={100,500,200,450,600};
        int[] pReaquest={212,417,112,426};
        System.out.println("For Worst-Fit");
        wf.sort(blocksort);
        wf.allocte(block, pReaquest, blocksort, blockCopy);
    }
}
