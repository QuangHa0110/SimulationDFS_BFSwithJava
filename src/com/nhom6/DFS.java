package com.nhom6;

import java.util.ArrayList;

public class DFS {
	private ArrayList<Integer> listPoint = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> listLineDFS = new ArrayList<ArrayList<Integer>>();
	private DataInput dataInput = new DataInput();
	private int numberPoint = dataInput.getMaxDinh();
	private int[] d = new int[numberPoint+1];// mảng đánh dấu những đỉnh đã được thăm nhưng chưa duyệt xong
	private int[] p = new int[numberPoint+1];// đỉnh từ đó thăm đỉnh tiếp theo
	private int[] f = new int[numberPoint+1];
	private char[] color = new char[numberPoint+1];
	public DFS() {
		dfs();
	}
	public void dfs(int u) {
		color[u] = 'G';
		listPoint.add(u);
		for (int i = 0; i < dataInput.getDanhSachDinh().get(u).size(); i++) {
			int v = dataInput.getDanhSachDinh().get(u).get(i);
			if (color[v] == 'W') {
				ArrayList<Integer> line = new ArrayList<Integer>();
				line.add(u);
				line.add(v);
				listLineDFS.add(line);
				p[v] = u;
				dfs(v);
			}
		}
		color[u]='B';
	}
	public void dfs() {
		for(int i=1;i<=numberPoint;i++) {
			color[i]='W';
		}
		for(int i=1;i<=numberPoint;i++) {
			if(color[i]=='W'){
					dfs(i);
			}
		}
	}

	public ArrayList<Integer> getListPoint() {
		return listPoint;
	}
	public void setListPoint(ArrayList<Integer> listPoint) {
		this.listPoint = listPoint;
	}
	public ArrayList<ArrayList<Integer>> getListLineDFS() {
		return listLineDFS;
	}
	public void setListLineDFS(ArrayList<ArrayList<Integer>> listLineDFS) {
		this.listLineDFS = listLineDFS;
	}
	

}
