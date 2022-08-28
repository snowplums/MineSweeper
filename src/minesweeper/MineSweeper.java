package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class MineSweeper {
	
	
	
	public int ROWS = 15;
	public int COLS = 20;
	
	
	public static int NUMBER_OF_MINES = 50;
	
	public static JFrame frame;
	
	public static MineSweeper game;
	
	
	public static Cell[][] grid;
	
	private JButton reset;

	public Container gridFormat = new Container();
	
	public static void main(String[] args) {
		game = new MineSweeper(20, 20);
	}
	
	
	public void gameOver() {
		Reset();
	}
	
	public void Reset() {
		addMines();
		setValues();
	}
	
	public Cell[][] getGrid(){
		return grid;
	}
	
	public int getROWS() {
		return ROWS;
	}
	
	public int getCOLS() {
		return COLS;
	}
	
	
	private final ActionListener menuButton = actionEvent -> {
        resetAll();
    };
	
	
	
	public MineSweeper(int gridRow, int gridCol) {
		ROWS = gridRow;
		COLS = gridCol;
		
		frame = new JFrame("MineSweeper");
		
		frame.setSize(1000, 1000);
		frame.setLayout(new BorderLayout());
		
		grid = new Cell[ROWS][COLS];
		
		generateMines();
		setValues();
		
		frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JPanel buttonPanel = new JPanel();
        reset = new JButton("Reset");
        reset.addActionListener(menuButton);
        buttonPanel.add(reset);
        frame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void resetAll() {
		//Arrays.fill(grid, null);
		for(Cell[] r : grid) {
			for(Cell c : r) {
				c.resetCell();
				c.setMine(false);
			}
		}
		
		
		
		addMines();
		setValues();
	}
	
	
	
	public void generateMines() {
		
        gridFormat.setLayout(new GridLayout(20, 20));
		
        for(int i = 0; i < ROWS; ++i) {
        	for(int j = 0; j < COLS; ++j) {
        		grid[i][j] = new Cell(i , j);
        		gridFormat.add(grid[i][j]);
        	}
        }
        
		
		addMines();
		
		frame.add(gridFormat, BorderLayout.CENTER);
		
		System.out.println("grid made");
	}
	
	
	public void addMines() {
		
		ArrayList<Integer> mineLocs = new ArrayList<Integer>();
		
		for(int i = 0; i < ROWS * COLS; ++i) {
			mineLocs.add(i);
		}
		
		Collections.shuffle(mineLocs);
		
		mineLocs.subList(NUMBER_OF_MINES - 1, mineLocs.size() - 1).clear();
		
		Collections.sort(mineLocs);
		
		for(int i : mineLocs) {
			System.out.println(i);
		}
		
		
		for(int i = 0; i < ROWS; ++i) {
			for(int j = 0; j < COLS; ++j) {
				
				if(mineLocs.size() != 0 && i * COLS + j == mineLocs.get(0)) {
					grid[i][j].setMine(true);
					mineLocs.remove(0);
				}
				System.out.println(i + ", " + j);
			}		
		}
		
		
		for(int i =0; i < mineLocs.size();++i)		
			System.out.println(mineLocs.get(i));
	}
	
	
	
		
	public void setValues() {
		for(Cell[] row : grid) {
			for(Cell c : row) {
				c.setValue();
			}
		}
	}
		
	public static String checkWin() {
		for(Cell[] cells : grid) {
			for(Cell c : cells) {
							
				if(!c.isMine() && c.isCovered()) {
					return "NOT DONE";
				}				
			}
		}
		
		return "WIN";
	}
}