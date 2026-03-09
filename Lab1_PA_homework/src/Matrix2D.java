public class Matrix2D{
    public static void main(String[] args){
        if(args.length<2){
            System.out.println("Eroare la linia de comanda");
            return;
        }
        int n=Integer.parseInt(args[0]);
        String forma=args[1];
        long startT=System.nanoTime();
        int[][] tablou=new int[n][n];

        if(forma.equals("rectangle")){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    tablou[i][j]=255;
                }
            }
            for(int i=n/4;i<3*n/4;i++){
                for(int j=n/4;j<3*n/4;j++){
                    tablou[i][j]=0;
                }
            }
        }else if(forma.equals("circle")){
            int raza=n/3;
            int centrux=n/2;
            int centruy=n/2;

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if ((i-centrux)*(i-centrux)+(j-centruy)*(j-centruy)<=raza*raza) {
                        tablou[i][j]=255;
                    }
                }
            }
        }else {
            System.out.println("Eroare: Forma incorecta");
            return;
        }

        long endT=System.nanoTime();
        long time=(endT-startT)/1000000;
        if(n<=150){
            String desen=createMatrix(tablou,n);
            System.out.println(desen);
        }else {
            System.out.println("Pentru n= "+ n+"a durat "+ time+"ms");
        }
    }
        private static String createMatrix(int[][] matrix,int n){
            String rez="";
            for(int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        rez+="\u25A0 ";
                    } else {
                        rez+="\u25A1 ";
                    }
                }
                rez+="\n";
            }
            return rez;
        }

}