package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import minesweeper.MineSweeper;

public class Cell extends JButton {
	
	
	private boolean mine; 
	private boolean covered = true;
	
	private int row;
	private int col;
	private int value;
	

	private final ActionListener actionListener = actionEvent -> {
        this.reveal();
        
        if(this.value == 0) {
        	this.blank();
        }
        
    };
	
	
	
	
	Cell[] neighbors = new Cell[8];
	
	public boolean isMine() {
		return mine;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isCovered() {
		return covered;
	}
	
	
	
	public Cell(int ROW, int COL, boolean MINE) {
		mine = MINE;
		
		if(mine) value = -1;
		
		row = ROW;
		col = COL;
		
		setText("");
		
		
		addActionListener(actionListener);
		
		
		
		
        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
				
	}
	
	public void setValue() {
		int num = 0;
		
		for(int r = -1; r <= 1; ++r) {
			for(int c = -1; c <= 1; ++c) {
				
				if(r == 0 && c == 0) {
					continue;
				}
				int rowTemp = r + row;
				int colTemp = c + col;
				
				if(rowTemp > MineSweeper.ROWS - 1 || rowTemp < 0) {
					continue;
				}
				
				if(colTemp > MineSweeper.COLS - 1 || colTemp < 0) {
					continue;
				}
				
				
				neighbors[num++] = MineSweeper.grid[rowTemp][colTemp];
			}
		}
		
		
		
		
		
		if(!mine) {
			int temp = 0;
			for(Cell c : neighbors) {
				if(c == null) {
					continue;
				}
				
				if(c.isMine()) {
					++temp;
				}				
			}
			value = temp;
		}
		
		
		
	}
	
	
	public void blank() {
		for(Cell c : neighbors) {
			if(c == null) {
				continue;
			}
			
			if(c.isCovered() && c.value != -1) {
				c.reveal();
				if(c.value == 0) {
					c.blank();
				}
				
				
				
				
			}
			
			
			
		}
	}

	
	
	
	public void reveal() {
		covered = false;
		
		if(mine) {
			System.out.println(mine);
			MineSweeper.gameOver();
		}
		setEnabled(false);
		setText(String.valueOf(value));
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

