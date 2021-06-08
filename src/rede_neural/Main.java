package rede_neural;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		NeuralNetwork rede = new NeuralNetwork(4, 70, 8);
		Double[] output = new Double[1];

		List<Treino> treinos = criarTreinos();

		Random random = new Random();
		int right = 0;
		Double differ;

		//Double[] x = new Double[1];
		//Double[] y = new Double[1];

		int i;
		int hist = 0;
		int rightSeq = 0;
		for(i = 0; i < 10000000; i++){
			int index = random.nextInt(treinos.size());
			rede.train(treinos.get(index).entradasArray(), treinos.get(index).RespostasArray());


			//x[0] = random.nextInt(10) * 1.0;
			//y[0] = ((7 * x[0]) + 10) / 100.0; 1

			//rede.train(x, y);

			
			right = 0;
			//double porc = 100.0/treinos.size();
			for(int j = 0; j < treinos.size(); j++){
				output = rede.feedforward(treinos.get(j).entradasArray());

				differ = testarEficacia(treinos.get(j).RespostasArray(), output);


				if(differ < 0.1)
					right ++;
			}

			if(rightSeq == 3){
				hist++;
				System.out.println("[" + (i + 1) + "] Acerto: " + hist + "/" + treinos.size());
			}
			
			if(hist == (treinos.size() - 1))	
				break;

			if(right > hist)
				rightSeq++;
			else
				rightSeq = 0;

			//if((porc*right) > 50)
			//	System.out.println("[" + (i + 1) + "] Acerto: " + (porc*right)+ "%");
		}

		Scanner scan = new Scanner(System.in);
		int op;
		do{
			scan = new Scanner(System.in);
			Double[] input = new Double[treinos.get(0).entradasArray().length];
			int num;
			for(int j = 0; j < treinos.get(0).entradasArray().length; j++){
				System.out.println("Numero: ");
				num = scan.nextInt();
				input[j] = num * 1.0;
			}

			Double[] res = rede.feedforward(input);

			System.out.printf("Resposta: ");
			for(int h = 0; h < 8; h++)
				System.out.printf("%.0f", res[h]);
			System.out.printf("\n");
			System.out.println("Continuar? 1-sim, 2-nao");
			op = scan.nextInt();

		} while(op != 2);
		scan.close();
	}

	private static List<Treino> criarTreinos() {
		
		List<Treino> treinos = new ArrayList<>();
																        //1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0
		treinos.add(new Treino(Arrays.asList(0.0,0.0,1.0,0.00), Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0)));
		treinos.add(new Treino(Arrays.asList(0.0,1.0,2.0,3.00), Arrays.asList(0.0,0.0,0.0,0.0,1.0,1.0,0.0,0.0)));
		treinos.add(new Treino(Arrays.asList(0.0,2.0,3.0,4.00), Arrays.asList(0.0,0.0,0.0,1.0,0.0,1.0,1.0,1.0)));
		treinos.add(new Treino(Arrays.asList(0.0,3.0,4.0,5.00), Arrays.asList(0.0,0.0,1.0,0.0,0.0,0.0,1.0,0.0)));
		treinos.add(new Treino(Arrays.asList(0.0,4.0,5.0,5.00), Arrays.asList(0.0,0.0,1.0,0.0,1.0,1.0,0.0,1.0)));
		treinos.add(new Treino(Arrays.asList(0.0,5.0,6.0,5.00), Arrays.asList(0.0,0.0,1.0,1.0,1.0,0.0,0.0,0.0)));
		treinos.add(new Treino(Arrays.asList(0.0,6.0,7.0,6.00), Arrays.asList(0.0,1.0,0.0,0.0,0.0,0.0,1.0,1.0)));
		treinos.add(new Treino(Arrays.asList(0.0,7.0,8.0,6.00), Arrays.asList(0.0,1.0,0.0,0.0,1.0,1.0,1.0,0.0)));
		treinos.add(new Treino(Arrays.asList(0.0,8.0,9.0,6.00), Arrays.asList(0.0,1.0,0.0,1.0,1.0,0.0,0.0,1.0)));

		treinos.add(new Treino(Arrays.asList(1.0,7.0,2.0,7.00), Arrays.asList(1.0,0.0,1.0,0.0,1.0,1.0,0.0,0.0)));
		treinos.add(new Treino(Arrays.asList(1.0,3.0,7.0,7.00), Arrays.asList(1.0,0.0,0.0,0.0,1.0,0.0,0.0,1.0)));

		return treinos;
	}


	private static Double testarEficacia(Double[] target, Double[] resposta){
		Double differ = 0.0;

		for(int i = 0; i < target.length; i++){

			if(target[i] > resposta[i])
				differ += target[i] - resposta[i];
			else
				differ += resposta[i] - target[i];
		}

		return (differ/target.length);
	}
	
}
