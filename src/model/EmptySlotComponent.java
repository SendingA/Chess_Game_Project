package model;

import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size);
        name='_';
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }
    public List<ChessboardPoint> canMoveToList(ChessComponent[][] chessComponents) {
        ArrayList<ChessboardPoint> canMoveToList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(canMoveTo(chessComponents,new ChessboardPoint(i,j))){
                    canMoveToList.add(new ChessboardPoint(i,j));
                }
            }
        }
        return canMoveToList;
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }

}
