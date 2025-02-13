package com.example.demo;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;


@Service
public class NewGame {

    final int SIZE = 5;
    int startnum=1;
    int endnum=15;
    int dataarrayindex=0;
    int k=0;

    public NewGame(){

    }

    private int[][] bingoarray = new int[SIZE][SIZE];
    private int[] bingocallarray = new int[12];
    private int[] bingodataarray = new int[SIZE*SIZE];
    private HashSet<Integer> uniquecheck = new HashSet<>();

    public int[][] getbingoarray(){
        return bingoarray;
    }

    public int[] getbingocallarray(){
        return bingocallarray;
    }
    
    public int[] newgame(){

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){

                if(i==2 && j==2){
                    bingoarray[i][j]=0;
                    continue;
                }

                int out=0;

                while (out==0) {

                int num = ThreadLocalRandom.current().nextInt(startnum, endnum + 1);

                if(!uniquecheck.contains(num))
                {
                    bingoarray[j][i]=num;
                    uniquecheck.add(num);
                    bingodataarray[dataarrayindex]=num;
                    dataarrayindex++;
                    out=1;

                }
                                  
                }
            }
            startnum +=15;
            endnum +=15;
        }
        
        int checklen=0;
        HashSet<Integer> checkdup = new HashSet<>();

        for(int i=0;i<12;i++){

            int out=0;

            while (out==0) {

            int num = ThreadLocalRandom.current().nextInt(0, 24);

            if(!checkdup.contains(num)){
                bingocallarray[checklen]=bingodataarray[num];
                checkdup.add(num);
                checklen++;
                out=1;
            }
        }
   
        }

        dataarrayindex=0;
        uniquecheck.clear();
        checkdup.clear();
        checklen=0;
        startnum=1;
        endnum=15;

        k=0;
        return bingodataarray;
    }

 
    public int[] callnum(){
        
        if(k<12){
        int num = bingocallarray[k];
        
        
        for(int i=0;i<bingoarray.length;i++){
            for(int j=0;j<bingoarray[i].length;j++){
                if(bingoarray[i][j]==num){
                    bingoarray[i][j]=0;
                }
            }
        }


        if(isVertical() || isHorizontal() || isDiagonal()){

            return new int[] {num,0} ;

        }

        k++;
    
       
        return new int[] {num,k};
    }

    return new int[] {0,12};
    }

    public boolean isVertical(){

        for(int i=0;i<5;i++){

            int sum=0;

            for(int j=0;j<5;j++){

                sum=sum+bingoarray[j][i];
            }
            if(sum==0){
                return true;
            }
        }

        return false;
    }

    public  boolean isDiagonal(){

        int sum=0;

        for(int i=0;i<5;i++)
            sum=sum+bingoarray[i][i];

        if(sum==0)  
            return true;

        int sumx=0;
        int l=4;
        
        for(int j=0;j<5;j++){   
            sumx=sumx+bingoarray[l][j];
            l--;
        }
            
        if(sumx==0)
            return true;
        
        return false;
    }

    public  boolean isHorizontal(){

        for(int i=0;i<5;i++){

            int sum=0;

            for(int j=0;j<5;j++){
                
                sum=sum+bingoarray[i][j];
            }
            if(sum==0){
                return true;
            }
        }
        return false;
    }

}

