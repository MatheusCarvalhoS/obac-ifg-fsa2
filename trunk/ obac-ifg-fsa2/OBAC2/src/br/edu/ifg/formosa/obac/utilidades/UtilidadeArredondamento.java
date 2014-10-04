package br.edu.ifg.formosa.obac.utilidades;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilidadeArredondamento {
	
	//Este m�todo � utilizado para diminuir o n�mero de casas decimais nos n�meros que ser�o
	//exibidos no painel de informa��es
	public static double arredondamento(int casas, double valor){
		return new BigDecimal(valor).setScale(casas, RoundingMode.HALF_DOWN).doubleValue();
	}
	
	public static String notacaoCientifica(double valor){
		int cont;
		for(cont=0; valor>10; cont++){	valor = valor/10;		}
		valor = arredondamento(2, valor);
		return (valor+"E"+cont);
	}
}
