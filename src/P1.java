import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.*;
import java.io.*;

class MyScanner {
	BufferedReader br;
	StringTokenizer st;
		 
	public MyScanner(FileInputStream f) {
		br = new BufferedReader(new InputStreamReader(f));
	}

	String next() throws IOException {
		while (st == null || !st.hasMoreElements())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}
		 
	int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	void close() throws IOException {
		br.close();
	}
}


public class P1 {
	static class Task {
		public static final String INPUT_FILE = "p1.in";
		public static final String OUTPUT_FILE = "p1.out";

		int n;

		ArrayList<Integer> dist = new ArrayList<>();

		// Citire input
		private void readInput() {
			try {
				MyScanner sc = new MyScanner(new FileInputStream(
								INPUT_FILE));
				n = sc.nextInt();

				dist.add(0, -1);
				for (int i = 1; i <= n; i++) {
					dist.add(i, sc.nextInt());
				}

				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		// Scriere output
		private void writeOutput(ArrayList<ArrayList<Integer>> result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
								OUTPUT_FILE)));
				int size = result.size();
				if (size == 0) {
					pw.printf("%d", -1);
					pw.close();
					return;
				}

				pw.printf("%d\n", size - 2);
				for (int i = 1; i < size; ++i) {
					int dim = result.get(i).size();
					for (int j = 0; j < dim; ++j) {
						pw.printf("%d %d\n", i, result.get(i).get(j));
					}
				}

				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		// Crearea unei liste de noduri cu nodurile adiacente
		private ArrayList<ArrayList<Integer>> getResult() {
			 ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

			 for (int i = 0; i <= n; ++i) {
			 	result.add(new ArrayList<Integer>());
			 }
		
			int distanta_curenta = 1;
			int nod_curent = 1;
			boolean find;
			boolean ok = true;
			int nr = 1;

			// Se va opri cand nu se va mai gasi distanta asociata pasului
			while (true) {
				find = false;
				for (int i = 1; i <= n; i++) {
					if (dist.get(i) == distanta_curenta) {
						result.get(nod_curent).add(i);
						++nr;
						find = true;
					} 
				}
				
				if (nr == n) {
					break;
				}

				if (!find) {
					ok = false;
					break;
				}

				distanta_curenta++;
				nod_curent = result.get(nod_curent).get(0);
			}

			// Nu s-au parcurs toate distantele
			if (!ok) {
				return new ArrayList<ArrayList<Integer>>();
			}

			return result;
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
