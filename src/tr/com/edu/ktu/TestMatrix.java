package tr.com.edu.ktu;

import java.util.Timer;
import java.util.TimerTask;

public class TestMatrix {
	static Timer timer;
	//Çarpma iþleminin süresini hesaplamak için
	static long startTime, endTime;
	static Matrix m1, m2;

	public static void initMatrices(int size) {
		System.out.println("Creating matrices...");
		m1 = Matrix.createRandomMatrix(size, size);
		m2 = Matrix.createRandomMatrix(size, size);
//		System.out.println("M1 = ");
//		m1.print();
//		System.out.println("M2 = ");
//		m2.print();
	}

	//Geçen süreyi yazan(saniye türünden) zamanlayýcýyý baþlatýr.
	public static void initTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int timePassedInSeconds = 0;
			@Override
			public void run() {
				System.out.println("Timer : " + (++timePassedInSeconds) + "s" + " passed...");
			}
		}, 1000, 1000);
	}

	//Geçen süreyi yazdýrmayý durdurur.
	public static void cancelTimer() {
		timer.cancel();
	}

	public static void commonMatrixMultiplicationTest(int matrixSize) {
		initMatrices(matrixSize);

		initTimer();
		System.out.println("Common matrix multiplication started...");
		startTime = System.currentTimeMillis();
		Matrix.multiply(m1, m2);
		endTime = System.currentTimeMillis();
		System.out.println("Common matrix multiplication finished...");
		System.out.print("Common matrix multiplication results : ");
		System.out.println("multiplication took " + (endTime - startTime) + "ms" + ", matrix size = " + matrixSize + "x" + matrixSize);
		cancelTimer();

		System.out.println("--------------------------------------------------------------------");
	}

	public static void linearMatrixMultiplicationTest(int matrixSize) {
		initMatrices(matrixSize);

		initTimer();
		System.out.println("Linear matrix multiplication started...");
		startTime = System.currentTimeMillis();
		Matrix.linearMultiply(m1, m2);
		endTime = System.currentTimeMillis();
		System.out.println("Linear matrix multiplication finished...");
		System.out.print("Linear matrix multiplication results : ");
		System.out.println("multiplication took "  + (endTime - startTime) + "ms" + ", matrix size = " + matrixSize + "x" + matrixSize);
		cancelTimer();

		System.out.println("--------------------------------------------------------------------");
	}

	public static void matrixMultiplicationWithThreadsTest1(int matrixSize) {
		initMatrices(matrixSize);

		initTimer();
		System.out.println("Common matrix multiplication started with 4 threads...");
		startTime = System.currentTimeMillis();
		MatrixMultipliyer multiplayer = new MatrixMultipliyer(m1, m2, matrixSize);
		multiplayer.initThreads1();
		multiplayer.startMultiplication();
		endTime = System.currentTimeMillis();
		System.out.println("Common matrix multiplication with 4 threads finished...");
		System.out.print("Common matrix multiplication with 4 threads results : ");
		System.out.println("multiplication took " + (endTime - startTime) + "ms" + ", matrix size = " + matrixSize + "x" + matrixSize);
		cancelTimer();

		System.out.println("--------------------------------------------------------------------");
	}

	public static void matrixMultiplicationWithThreadsTest2(int matrixSize) {
		initMatrices(matrixSize);

		initTimer();
		System.out.println("Common matrix multiplication started with 4 threads...");
		startTime = System.currentTimeMillis();
		MatrixMultipliyer multiplayer = new MatrixMultipliyer(m1, m2, matrixSize);
		multiplayer.initThreads2();
		multiplayer.startMultiplication();
		endTime = System.currentTimeMillis();
		System.out.println("Common matrix multiplication with 4 threads finished...");
		System.out.print("Common matrix multiplication with 4 threads results : ");
		System.out.println("multiplication took " + (endTime - startTime) + "ms" + ", matrix size = " + matrixSize + "x" + matrixSize);
		cancelTimer();

		System.out.println("--------------------------------------------------------------------");
	}

	public static void main(String[] args) {
		System.out.println("Matrix multiplication test is starting...");
		matrixMultiplicationWithThreadsTest1(1000);
		commonMatrixMultiplicationTest(1000);
	}
}
