package rede_neural;

public class NeuralNetwork {

	private int iNodes;
	private int hNodes;
	private int oNodes;
	
	private Double learningRate;
	private Matriz biasIH;
	private Matriz biasHO;
	
	private Matriz weightsIH;
	private Matriz weightsHO;
	
	public NeuralNetwork(int iNodes, int hNodes, int oNodes) {
		this.hNodes = hNodes;
		this.iNodes = hNodes;
		this.oNodes = oNodes;
		
		biasIH = new Matriz(hNodes, 1);
		biasHO = new Matriz(oNodes, 1);

		this.learningRate = 0.1;
		this.weightsIH = new Matriz(hNodes, iNodes);
		this.weightsHO = new Matriz(oNodes, hNodes);
	}

	
	public Double[] feedforward(Double[] inputArray) {
		// INPUT -> HIDDEN
		Matriz input = Matriz.arrayToMatriz(inputArray);
		Matriz hidden = Matriz.multiply(this.weightsIH, input);
		hidden = Matriz.add(hidden, this.biasIH);
		hidden.activationSigmoid();
		
		// HIDDEN -> OUTPUT
		Matriz output = Matriz.multiply(this.weightsHO, hidden);
		output = Matriz.add(output, this.biasHO);
		output.activationSigmoid();
		
		return Matriz.MatrizToArray(output);
	}
	
	public void train(Double[] inputArray, Double[] targetArray){
		// INPUT -> HIDDEN
		Matriz input = Matriz.arrayToMatriz(inputArray);
		Matriz hidden = Matriz.multiply(this.weightsIH, input);
		hidden = Matriz.add(hidden, this.biasIH);
		hidden.activationSigmoid();

		// HIDDEN -> OUTPUT
		Matriz output = Matriz.multiply(this.weightsHO, hidden);
		output = Matriz.add(output, this.biasHO);
		output.activationSigmoid();

		
		// BACKPROPAGATION

		// OUTPUT -> HIDDEN
		Matriz target = Matriz.arrayToMatriz(targetArray);
		Matriz outputError = Matriz.subtract(target, output);
		Matriz d_output = output.derivateSigmoid();
		Matriz hiddenT = hidden.trasposeMatriz();

		Matriz gradient = Matriz.hadamard(d_output, outputError);
		gradient = Matriz.scalarMultiply(gradient, this.learningRate);

		this.biasHO = Matriz.add(this.biasHO, gradient);

		Matriz weightsHODeltas = Matriz.multiply(gradient, hiddenT);
		this.weightsHO = Matriz.add(this.weightsHO, weightsHODeltas);

		// HIDDEN -> INPUT
		Matriz weightsHOT = this.weightsHO.trasposeMatriz();
		Matriz hiddenError = Matriz.multiply(weightsHOT, outputError);
		Matriz d_hidden = hidden.derivateSigmoid();
		Matriz inputT = input.trasposeMatriz();

		Matriz gradientH = Matriz.hadamard(d_hidden, hiddenError);
		gradientH = Matriz.scalarMultiply(gradientH, this.learningRate);

		this.biasIH = Matriz.add(this.biasIH, gradientH);

		Matriz weightsIHDeltas = Matriz.multiply(gradientH, inputT);
		this.weightsIH = Matriz.add(this.weightsIH, weightsIHDeltas);
	}
	
	public int getiNodes() {
		return iNodes;
	}

	public void setiNodes(int iNodes) {
		this.iNodes = iNodes;
	}

	public int gethNodes() {
		return hNodes;
	}

	public void sethNodes(int hNodes) {
		this.hNodes = hNodes;
	}

	public int getoNodes() {
		return oNodes;
	}

	public void setoNodes(int oNodes) {
		this.oNodes = oNodes;
	}

	public Matriz getBiasIH() {
		return biasIH;
	}

	public void setBiasIH(Matriz biasIH) {
		this.biasIH = biasIH;
	}

	public Matriz getBiasHO() {
		return biasHO;
	}

	public void setBiasHO(Matriz biasHO) {
		this.biasHO = biasHO;
	}
}
