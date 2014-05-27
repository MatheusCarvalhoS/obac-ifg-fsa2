package br.edu.ifg.formosa.obac.controle.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;

public class ControlePainelConfiguracaoAtualizacoes {
	
	public ControlePainelConfiguracaoAtualizacoes(final VisaoPainelConfiguracao vpc) {
		
		//Mudan�a nos r�tulos de dados da propuls�o e nas simula��es dispon�veis
		vpc.getCsPropulsao().addActionListener(new ActionListener() {
			private String canhao = "Canh�o";
			private String mola = "Mola";
			private String dado1Canhao = "�ngulo (�)"; 
			private String dado2Canhao = "Energia (J)";
			private String dado1Mola = "Tamaho da Mola(m)";
			private String dado2Mola = "Constante El�stica (N/m)";
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(vpc.getCsPropulsao().getSelectedItem().equals(canhao)){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(dado1Canhao);
					vpc.getrPropulsaoDado2().setText(dado2Canhao);
					//Caixa de sele��o das simula��es
				}
				else if(vpc.getCsPropulsao().getSelectedItem().equals(mola)){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(dado1Mola);
					vpc.getrPropulsaoDado2().setText(dado2Mola);
					//Caixa de sele��o das simula��es
				}
				else{
					JOptionPane.showMessageDialog(null,
						"Falha catastr�fica no funcionamento do programa", 
						"Aviso", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		
		//Coeficiente de restitui��o
		vpc.getdObjetoCoeficienteRestituicao().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vpc.getrObjetoAtualCoefRest().setText(""+(vpc.getdObjetoCoeficienteRestituicao().getValue()/100.0));
				//O valor do Deslizante (JSlider) � pego e dividido por 100
				//Em seguida � passado para um r�tulo (JLabel), afim do usu�rio saber o valor que a simula��o usar�
			}
		});
	}

}
