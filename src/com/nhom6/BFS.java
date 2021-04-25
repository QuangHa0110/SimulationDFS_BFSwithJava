package com.nhom6;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
	private ArrayList<Integer> listPoint = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> listLineBFS = new ArrayList<ArrayList<Integer>>();
	private Queue<Integer> Q = new LinkedList<>();
	private DataInput dataInput = new DataInput();
	private int numberPoint = dataInput.getMaxDinh();
	private char[] color = new char[numberPoint + 1];
	public BFS() {
		bfs();
	}
	public void bfs(int u) {
		Q.add(u);
		color[u] = 'G';
	
		while (!Q.isEmpty()) {
			int v = Q.peek();
			Q.poll();
			listPoint.add(v);
			for (int i : dataInput.getDanhSachDinh().get(v)) {
				if (color[i] == 'W') {
					ArrayList<Integer> line = new ArrayList<Integer>();
					line.add(v);
					line.add(i);
					listLineBFS.add(line);
					color[i] = 'G';
					Q.add(i);
				}
			}
		}
	}

	public void bfs() {
		for (int i = 1; i <= numberPoint; i++) {
			color[i] = 'W';
		}
		for (int i = 1; i <= numberPoint; i++) {
			if (color[i] == 'W') {
				bfs(i);
			}
		}
	}

	

	public ArrayList<Integer> getListPoint() {
		return listPoint;
	}

	public void setListPoint(ArrayList<Integer> listPoint) {
		this.listPoint = listPoint;
	}

	public ArrayList<ArrayList<Integer>> getListLineBFS() {
		return listLineBFS;
	}

	public void setListLineBFS(ArrayList<ArrayList<Integer>> listLineBFS) {
		this.listLineBFS = listLineBFS;
	}

}
