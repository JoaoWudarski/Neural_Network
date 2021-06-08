package rede_neural;

public class Matriz {

	private int rows;
	private int cols;
	private Double[][] data;
	
	public Matriz(int rows, int cols) {

		this.rows = rows;
		this.cols = cols;
		this.data = new Double[rows][cols];
		
		randomize();
	}
	
	public Matriz trasposeMatriz() {
		Matriz mat = new Matriz(this.cols, this.rows);

		for(int i = 0; i < mat.getRows(); i++) {
			for(int j = 0; j < mat.getCols(); j++) {
				mat.getData()[i][j] = this.data[j][i];
			}
		}
		return mat;
	}

	public static Matriz scalarMultiply(Matriz a, Double num) {
		Matriz mat = new Matriz(a.getRows(), a.getCols());
		
		for(int i = 0; i < mat.getRows(); i++) {
			for(int j = 0; j < mat.getCols(); j++) {
				mat.getData()[i][j] = a.getData()[i][j] * num;
			}
		}
		return mat;
	}

	public static Matriz hadamard(Matriz a, Matriz b) {
		Matriz mat = new Matriz(a.getRows(), a.getCols());
		
		for(int i = 0; i < mat.getRows(); i++) {
			for(int j = 0; j < mat.getCols(); j++) {
				mat.getData()[i][j] = a.getData()[i][j] * b.getData()[i][j];
			}
		}
		return mat;
	}

	public static Matriz add(Matriz a, Matriz b) {
		Matriz mat = new Matriz(a.getRows(), a.getCols());
		
		for(int i = 0; i < mat.getRows(); i++) {
			for(int j = 0; j < mat.getCols(); j++) {
				mat.getData()[i][j] = a.getData()[i][j] + b.getData()[i][j];
			}
		}
		return mat;
	}

	public static Matriz subtract(Matriz a, Matriz b) {
		Matriz mat = new Matriz(a.getRows(), a.getCols());
		
		for(int i = 0; i < mat.getRows(); i++) {
			for(int j = 0; j < mat.getCols(); j++) {
				mat.getData()[i][j] = a.getData()[i][j] - b.getData()[i][j];
			}
		}
		return mat;
	}
	
	public static Matriz multiply(Matriz a, Matriz b) {
		Matriz mat = new Matriz(a.getRows(), b.getCols());
		
		for(int i = 0; i < mat.getData().length; i++) {
			for(int j = 0; j < mat.getData()[i].length; j++) {
				mat.getData()[i][j] = 0.0;
				for(int k = 0; k < a.getData()[i].length; k++) {
					mat.getData()[i][j] += a.getData()[i][k] * b.getData()[k][j];
				}
			}
		}
		
		return mat;	
	}
	
	public void randomize() {
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.cols; j++) {
				this.data[i][j] = (Math.random() * 2) - 1;
			}
		}
	}
	
	public static Matriz arrayToMatriz(Double[] array) {
		Matriz mat = new Matriz(array.length, 1);
		
		for(int i = 0; i < mat.getRows(); i++) {
			mat.getData()[i][0] = array[i];
		}
		
		return mat;
	}

	public static Double[] MatrizToArray(Matriz mat) {
		Double[] array = new Double[mat.getRows()];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = mat.getData()[i][0];
		}
		
		return array;
	}
	
	public void activationSigmoid() {
		
		for(int i = 0; i < this.rows; i++) {
			this.data[i][0] = sigmoid(this.data[i][0]);
		}
	}

	public Matriz derivateSigmoid() {
		
		Matriz mat = new Matriz(this.rows, this.cols);

		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.cols; j++){
				mat.getData()[i][j] = dSigmoid(this.data[i][j]);
			}
		}
		return mat;
	}
	
	private Double sigmoid(Double x) {
		return 1 / (1 + Math.exp(-x));
	}

	private Double dSigmoid(Double x) {
		return x * (1 - x);
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public Double[][] getData() {
		return data;
	}

	public void setData(Double[][] data) {
		this.data = data;
	}
	
	public String getDataFormated() {
		String format = "";
		
		for(int i = 0; i < rows; i++) {
			format = format.concat("[");
			for(int j = 0; j < cols; j++) {
				if(j == cols-1)
					format = format.concat(String.valueOf(this.data[i][j]) +"]\n");
				else
					format = format.concat(String.valueOf(this.data[i][j]) + ", ");
			}
		}
		return format;
	}
}
