package br.edu.ifg.formosa.obac.utilidades;

import br.edu.ifg.formosa.obac.modelo.ModeloEscala;

public class UtilidadeConvercoesEscala {
	
	/*Legenda das vari�veis utilizadas nos m�todos abaixo:
	 *---pTotal -> Tamanho/Posi��o total em Pixel (Tamanho da escala/mola em PIXELS)
	 *---pAtual -> Tamanho/Posi��o atual em Pixel (Tamanho da mola/Posi��o do Objeto em PIXEL)
	 *---mTotal -> Tamanho/Posi��o total em Metro (Tamanho da escala/mola em METROS)
	 *---mAtual -> Tamanho/Posi��o atual em Metro (Tamanho da mola/Posi��o do Objeto em METROS)
	 */
	
	//M�todo usado para converter METRO em PIXEL -> p'=m'*p/m 
	public static int converteMetroEmPixelX(double pTotal, double mAtual, double mTotal){
		int pAtual = (int)((pTotal*mAtual)/mTotal);
		return pAtual;
    }//Fim converteMetroEmPixelX	
	
	//M�todo usado para converter PIXEL em METRO -> m'=m*p'/p
	public static int convertePixelMetro(double mTotal, double pAtual, double pTotal){
		int mAtual = (int)(mTotal*pAtual/pTotal);
		return mAtual;
	}//Fim convertePixelMetro
	
	
	
	public static int metroParaPixelH(ModeloEscala mEH, double metro) {
		return (int) ((metro * mEH.getEscalaFimXPix() - mEH.getEscalaInicioX()) / mEH.getMarcadoresEscala()[mEH.qtdMarcadores] + mEH.getEscalaInicioX());
	}
	public static int pixelParaMetroH(ModeloEscala mEH, double pixel) {
		return (int) (pixel * mEH.getMarcadoresEscala()[mEH.qtdMarcadores]) / mEH.getEscalaFimXPix();
	}
	
	public static int metroParaPixelV(ModeloEscala mEV, double metro) {
		return (int) (((metro * (mEV.getEscalaFimYPix() - mEV.getEscalaInicioY())) / mEV.getMarcadoresEscala()[mEV.qtdMarcadores]) + mEV.getEscalaInicioY());
	}
	public static int pixelParaMetroV(ModeloEscala mEV, double pixel) {
		return (int) (pixel * mEV.getMarcadoresEscala()[mEV.qtdMarcadores]) / mEV.getEscalaFimYPix();
	}
}
