
package rede_neural;

import java.util.List;

public class Treino {

    private List<Double> entradas;
    private List<Double> respostas;


    
    public Treino(List<Double> entradas, List<Double> respostas) {

        this.entradas = entradas;
        this.respostas = respostas;
    }

    public List<Double> getEntradas() {
        return entradas;
    }
    public void setEntradas(List<Double> entradas) {
        this.entradas = entradas;
    }
    public List<Double> getRespostas() {
        return respostas;
    }
    public void setRespostas(List<Double> respostas) {
        this.respostas = respostas;
    }

    public Double[] entradasArray(){
        Double[] arr = new Double[this.entradas.size()];

        for(int i = 0; i < arr.length; i++){
            arr[i] = this.entradas.get(i);
        }
        return arr;
    }

    public Double[] RespostasArray(){
        Double[] arr = new Double[this.respostas.size()];

        for(int i = 0; i < arr.length; i++){
            arr[i] = this.respostas.get(i);
        }
        return arr;
    }
    
}
