package com.nhom6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;


public class GraphicsPanel extends JPanel {

	private Color background = Color.lightGray;
	private Point centerPoint;
	private int R;
	private int numberPoint;
	private ArrayList<MyPoint> listPoint = new ArrayList<MyPoint>();
	private ArrayList<MyLine> listLine = new ArrayList<MyLine>();
	private DFS dfs = new DFS();
	private BFS bfs = new BFS();

	public GraphicsPanel() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(background);
		drawGraph(g);
	}

	public void start(int width, int height) {
		listPoint.clear();
		listLine.clear();
		centerPoint = new Point(getWidth() / 2, getHeight() / 2);
		R = (centerPoint.x > centerPoint.y) ? centerPoint.y : centerPoint.x;
		R = R * 4 / 5;
		createGraph();
		repaint();
	}

	private void createGraph() {
		for (int i = 0; i < numberPoint; i++) {
			double phi = -90 + 360.0 * i / numberPoint;
			phi = phi * Math.PI / 180;

			int x = centerPoint.x + (int) (R * Math.cos(phi));
			int y = centerPoint.y + (int) (R * Math.sin(phi));

			listPoint.add(new MyPoint(x, y, listPoint.size() + 1));
		}
	}

	private void drawGraph(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < listLine.size(); i++) {
			if (listLine.get(i).getIndexP1() < listLine.get(i).getIndexP2()) {
				listLine.get(i).drawLineMiddle(g2);
				listLine.get(i).drawArrowMiddle(g2);
			} else {
				listLine.get(i).drawArrowLeft(g2);
				listLine.get(i).drawLineLeft(g2);
			}
		}
		for (int i = 0; i < listPoint.size(); i++) {
			listPoint.get(i).draw(g2);
		}

	}

	public void addLine() {
		DataInput dataInput = new DataInput();
		for (int i = 1; i <= dataInput.getMaxDinh(); i++) {
			for (Integer j : dataInput.getDanhSachDinh().get(i)) {
				listLine.add(new MyLine(listPoint.get(i - 1).getCenter(), listPoint.get(j - 1).getCenter(), i, j));
			}
		}

		repaint();
	}

	public void addLineDFS(int count) {
		if (count < dfs.getListPoint().size()) {
			if (count < dfs.getListLineDFS().size()) {
				for (int i = 0; i < listLine.size(); i++) {
					if (listLine.get(i).getIndexP1() == dfs.getListLineDFS().get(count).get(0)
							&& listLine.get(i).getIndexP2() == dfs.getListLineDFS().get(count).get(1)) {
						this.listLine.get(i).setType(1);
						this.listPoint.get(dfs.getListPoint().get(count) - 1).setType(2);
					}
				}
			} else {
				this.listPoint.get(dfs.getListPoint().get(count) - 1).setType(1);
			}
			if (count == 0) {
				this.listPoint.get(dfs.getListPoint().get(count) - 1).setType(3);
			}
			repaint();
		} else
			return;

	}

	public void addLineBFs(int count) {
		if (count < bfs.getListPoint().size()) {

			if (count < bfs.getListLineBFS().size()) {

				for (int i = 0; i < listLine.size(); i++) {

					if (listLine.get(i).getIndexP1() == bfs.getListLineBFS().get(count).get(0)
							&& listLine.get(i).getIndexP2() == bfs.getListLineBFS().get(count).get(1)) {
						this.listLine.get(i).setType(1);
						this.listPoint.get(bfs.getListPoint().get(count) - 1).setType(2);
					}

				}
			} else {
				this.listPoint.get(bfs.getListPoint().get(count) - 1).setType(1);
			}
			if (count == 0) {
				this.listPoint.get(bfs.getListPoint().get(count) - 1).setType(3);
			}

			repaint();
		} else
			return;

	}

	public int getNumberPoint() {
		return numberPoint;
	}

	public void setNumberPoint(int numberPoint) {
		this.numberPoint = numberPoint;
	}

	public ArrayList<MyPoint> getListPoint() {
		return listPoint;
	}

	public void setListPoint(ArrayList<MyPoint> listPoint) {
		this.listPoint = listPoint;
	}

	public ArrayList<MyLine> getListLine() {
		return listLine;
	}

	public void setListLine(ArrayList<MyLine> listLine) {
		this.listLine = listLine;
	}

}
