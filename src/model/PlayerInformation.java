package model;

import java.io.*;
import java.util.List;

public class PlayerInformation {
    private String username;
    private String password;
    private int score;
    private double winningPercentage;
    private int total;

    public PlayerInformation(String username, String password, int score, double winningPercentage,int total) {
        this.username = username;
        this.password = password;
        this.score = score;
        this.winningPercentage = winningPercentage;
        this.total=total;
    }
    public PlayerInformation(String username){
        this.username = username;
        this.password=getPassword(username);
        this.total=getTotal(username);
        this.score=getScore(username);
        this.winningPercentage=getWinningPercentage(username);
    }

    @Override
    public String toString() {
        return  password+"\n" + score+"\n" + String.format("%.2f",winningPercentage)+"\n"+total;
    }
//更新文件内容
    public void newFile(){
        String oldPath= "./PlayerList/"+username+".txt";
        String newPath="./PlayerList/temp.txt";
        BufferedWriter w=null;
        try{
            w=new BufferedWriter(new FileWriter(newPath));
            w.write(this.toString());
        }catch (Exception e){
            return;
        }finally {
            try {
                if (w != null) {
                    w.close();
                }
            }
            catch (IOException e){}
        }
        File oldFile=new File(oldPath);
        oldFile.delete();
        File newFile=new File(newPath);
        newFile.renameTo(oldFile);
    }
    //赢一局
    public void win(){
        this.score+=1;
        this.total+=1;
        this.winningPercentage=(double)score/total;
        newFile();
    }
    //输一局
    public void defeat(){
        this.total+=1;
        this.winningPercentage=(double)score/total;
        newFile();
    }

    public static int getScore(String username) {
        String path="./PlayerList/"+username+".txt";
        File file=new File(path);
        FileReader r;
        int row=0;
        try{
            r=new FileReader(file);
            BufferedReader br=new BufferedReader(r);
            String line;
            while((line=br.readLine())!=null){
                row++;
                if(row==2){
                    return Integer.parseInt(line);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static int getTotal(String username) {
        String path="./PlayerList/"+username+".txt";
        File file=new File(path);
        FileReader r;
        int row=0;
        try{
            r=new FileReader(file);
            BufferedReader br=new BufferedReader(r);
            String line;
            while((line=br.readLine())!=null){
                row++;
                if(row==4){
                    return Integer.parseInt(line);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static String getPassword(String username){
        String path="./PlayerList/"+username+".txt";
        File file=new File(path);
        FileReader r;
        int row=0;
        try{
            r=new FileReader(file);
            BufferedReader br=new BufferedReader(r);
            String line;
            while((line=br.readLine())!=null){
                row++;
                if(row==3){
                    return line;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
    public static double getWinningPercentage(String username) {
        String path="./PlayerList/"+username+".txt";
        File file=new File(path);
        FileReader r;
        int row=0;
        try{
            r=new FileReader(file);
            BufferedReader br=new BufferedReader(r);
            String line;
            while((line=br.readLine())!=null){
                row++;
                if(row==3){
                    return Double.parseDouble(line);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }
}
