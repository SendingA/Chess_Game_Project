package controller;

import model.ChessColor;
import model.EmptySlotComponent;
import model.PlayerInformation;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {
    private Chessboard chessboard;
    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public List<String> loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            if (chessboard.loadGame(chessData) == 101)
                JOptionPane.showMessageDialog(null, "棋盘并非8x8\n错误编码：101", "加载失败", JOptionPane.INFORMATION_MESSAGE);
            else if (chessboard.loadGame(chessData) == 102)
                JOptionPane.showMessageDialog(null, "棋盘并非六种之一，棋子并非黑白棋子\n错误编码：102", "加载失败", JOptionPane.INFORMATION_MESSAGE);
            else if (chessboard.loadGame(chessData) == 103)
                JOptionPane.showMessageDialog(null, "缺少行棋方\n错误编码：103", "加载失败", JOptionPane.INFORMATION_MESSAGE);
            return chessData;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "文件格式错误或找不到文件\n错误编码：104", "加载失败", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getChessboardGraph() {
        ArrayList<String> chessBoard = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            String row="";
            for (int j = 0; j < 8; j++) {
                row=row+chessboard.getChessComponents()[i][j].name;
            }
            chessBoard.add(row);
        }
        if(chessboard.getCurrentColor()== ChessColor.WHITE){
            chessBoard.add("w");
        }else if(chessboard.getCurrentColor()== ChessColor.BLACK)
            chessBoard.add("b");
        return chessBoard;
    }
    public boolean storeChessboard(){
        ArrayList<String> chessBoard= (ArrayList<String>) getChessboardGraph();
        String name = JOptionPane.showInputDialog("输入存档名：", "");
        if (name != null) {
            String path = "./resource/" + name + ".txt";
            File file = new File(path);
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(file);
                for (int i = 0; i < 9; i++) {
                    fileWriter.write(chessBoard.get(i) + "\n");
                }
                //将字符串写入到指定的路径下的文件中
                fileWriter.close();
                return true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }



    public void record(){
        ArrayList<String> chessBoard= (ArrayList<String>) getChessboardGraph();
            String path = "./record/" + chessboard.count + ".txt";
            File file = new File(path);
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(file);
                for (int i = 0; i < 9; i++) {
                    fileWriter.write(chessBoard.get(i) + "\n");
                }
                //将字符串写入到指定的路径下的文件中
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }









    public Chessboard getChessboard() {
        return chessboard;
    }
}
