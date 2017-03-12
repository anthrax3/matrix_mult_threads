package tr.com.edu.ktu;

import java.util.Random;

public class Matrix {
	private float[][] data;
	private int rowNum;
	private int colNum;

	public Matrix(int rowNum, int colNum) {
		this.data   = new float[rowNum][colNum];
		this.rowNum = rowNum;
		this.colNum = colNum;
	}

	public int getRowNum() {
		return rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public float get(int row, int col) {
		return data[row][col];
	}

	public void set(int row, int col, float value) {
		data[row][col] = value;
	}

	public static Matrix multiply(Matrix m1, Matrix m2) {
		Matrix result = new Matrix(m1.rowNum, m2.colNum);
		for (int i = 0; i < m1.rowNum; i++) {
		    for (int j = 0; j < m2.colNum; j++) {
		        for (int k = 0; k < m1.colNum; k++) {
		            result.set(i, j, m1.get(i, k) * m2.get(k, j));
		        }
		    }
		}
		return result;
	}

	public static Matrix multiply(Matrix m1, Matrix m2, int rowLimit1, int rowLimit2) {
		Matrix result = new Matrix(m1.rowNum, m2.colNum);
		for (int i = rowLimit1; i < rowLimit2; i++) {
		    for (int j = 0; j < m2.colNum; j++) {
		        for (int k = 0; k < m1.colNum; k++) {
		            result.set(i, j, m1.get(i, k) * m2.get(k, j));
		        }
		    }
		}
		return result;
	}

	public static Matrix multiply(Matrix m1, Matrix m2, int rowLimit1, int rowLimit2, int collimit) {
		Matrix result = new Matrix(m1.rowNum, m2.colNum);
		for (int i = rowLimit1; i < rowLimit2; i++) {
		    for (int j = 0; j < collimit; j++) {
		        for (int k = 0; k < m1.colNum; k++) {
		            result.set(i, j, m1.get(i, k) * m2.get(k, j));
		        }
		    }
		}
		return result;
	}

	public static Matrix linearMultiply(Matrix m1, Matrix m2) {
		if((m1.rowNum != m2.rowNum) || (m1.colNum != m2.colNum)) {
			throw new IllegalArgumentException("row and column number must be the same...");
		}
		Matrix result = new Matrix(m1.rowNum, m2.colNum);
		for (int i = 0; i < result.rowNum; i++) {
			for (int j = 0; j < result.colNum; j++) {
				result.set(i, j, m1.get(i, j) * m2.get(i, j));
			}
		}
		return result;
	}

	public static Matrix createRandomMatrix(int rowNum, int colNum) {
		Random random = new Random();
		Matrix mat = new Matrix(rowNum, colNum);
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				mat.set(i, j, random.nextFloat());
			}
		}
		return mat;
	}

	public void print() {
		StringBuilder builder = new StringBuilder();
		for (float[] fs : data) {
			for (float f : fs) {
				builder.append(f).append(" ");
			}
			builder.append("\n");
		}
		builder.append("--------------------------------------------------------");
		System.out.println(builder.toString());
	}

}
