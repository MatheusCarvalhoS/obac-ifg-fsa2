package br.edu.ifg.formosa.obac.controle.escala;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import br.edu.ifg.formosa.obac.modelo.ModeloEscala;
import br.edu.ifg.formosa.obac.visao.VisaoEscala;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;

public class ControleEscala {
	//Metodos
	//--Construtor
	public ControleEscala(VisaoEscala vE, ModeloEscala mE, final VisaoPainelConfiguracao vPC) {
		vE = new VisaoEscala(100, 700, 564, 564, 5, 0, mE);
	}
	
	//--Metodo utilizado para deteminar espacamento dos marcadores na escala
	public static int retornaPedaco(int inicio, int fim, int marcadores) {
		return (fim - inicio) / (marcadores + 1);
		/*
		 * Explicacao 'marcadores + 1'
		 * Se o numero de marcadores for 1, a linha (escala) tera de ser dividida em duas partes (1 mar + 1 = 2 partes)
		 * Se o numero de marcadores for 8, a linha (escala) tera de ser dividida em nove partes (8 mar + 1 = 9 partes)
		 */
	}
	
	//--Altera os valores do ModeloEscala

}