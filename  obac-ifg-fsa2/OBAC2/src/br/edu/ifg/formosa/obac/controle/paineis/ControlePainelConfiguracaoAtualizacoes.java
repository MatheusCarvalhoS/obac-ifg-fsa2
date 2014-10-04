package br.edu.ifg.formosa.obac.controle.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.edu.ifg.formosa.obac.controle.obac.ControleOBAC;
import br.edu.ifg.formosa.obac.controle.obstaculo.ControleObstaculoMouse;
import br.edu.ifg.formosa.obac.modelo.ModeloAmbiente;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelConfiguracao;
import br.edu.ifg.formosa.obac.modelo.ModeloPainelFormulas;
import br.edu.ifg.formosa.obac.modelo.ModeloPropulsao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelConfiguracao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelFormulas;
import br.edu.ifg.formosa.obac.visao.VisaoPainelInformacao;
import br.edu.ifg.formosa.obac.visao.VisaoPainelSimulacao;

public class ControlePainelConfiguracaoAtualizacoes {
	
	//Constantes
	private final ModeloAmbiente mA;
	private final VisaoPainelConfiguracao vpc;
	private final ModeloPainelConfiguracao mpc;
	private final VisaoPainelFormulas vpf;
	private final VisaoPainelInformacao vpi;
	private final VisaoPainelSimulacao vPS;
	private final ModeloPropulsao mP;
	private final ControleObstaculoMouse com;
	private final ControleOBAC cOBAC;
	
	//Construtor
	public ControlePainelConfiguracaoAtualizacoes(
		   ModeloAmbiente mA,
		   VisaoPainelConfiguracao vpc, ModeloPainelConfiguracao mpc,
		   VisaoPainelFormulas vpf, VisaoPainelInformacao vpi, VisaoPainelSimulacao vPS,
		   ModeloPropulsao mP, ControleObstaculoMouse com, ControleOBAC cOBAC)
	{
		this.mA = mA;
		this.vpc = vpc;
		this.mpc = mpc;
		this.vpf = vpf;
		this.vpi = vpi;
		this.vPS = vPS;
		this.mP = mP;
		this.com = com;
		this.cOBAC = cOBAC;
		
		acaoPropulsoes();
		acaoCoefDeRestituicao();
		acaoExibirColisao();
		
	}
	
	//Altera��es realizadas de acordo com a propuls�o selecionada
	private void acaoPropulsoes(){
		vpc.getCsPropulsao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Propuls�o pelo canh�o
				if(vpc.getCsPropulsao().getSelectedItem().equals(mpc.getCanhao())){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(mpc.getDado1Canhao());
					vpc.getrPropulsaoDado2().setText(mpc.getDado2Canhao());
					vpc.getCtPropulsaoDado2().setEnabled(true);
					//Caixa de sele��o das simula��es
						//Teste l�gico para n�o remover linha do QO
						if(vpc.getCsAmbienteSimulacao().getItemCount()==6)
							vpc.getCsAmbienteSimulacao().removeItemAt(vpc.getCsAmbienteSimulacao().getItemCount()-1);//Remove a op��o de QL
						//Teste l�gico para que adicione apenas uma vez o LO
						if(vpc.getCsAmbienteSimulacao().getItemCount()==4)
							vpc.getCsAmbienteSimulacao().insertItemAt(mpc.getLancamentoObliquo(), vpc.getCsAmbienteSimulacao().getItemCount());
					//Painel de F�rmulas
					vpf.getAtVInicial().setText(ModeloPainelFormulas.propCanhao);
					//Painel de Informa��es
					vpi.setVisivelCanhao();
					//Altera a imagem
					mP.trocaImagemProp(true);
					vPS.getVisaoPropulsao().setImagemPropulsao(mP.getImagemPropulsao());
					vPS.getVisaoAuxiliar().getpCompressor().setVisible(false);
					//Defazendo as edi��es espec�ficas da QL 
					vPS.getVisaoPropulsao().setVisible(true);
					vpc.getCsAmbienteSimulacao().setEnabled(true);
					//M�todo necess�rio para corrigir o campo de dado 1 da propuls�o por canh�o
					ajustesPConfig();
				}
				//Propuls�o pela mola
				else if(vpc.getCsPropulsao().getSelectedItem().equals(mpc.getMola())){
					//R�tulos
					vpc.getrPropulsaoDado1().setText(mpc.getDado1Mola());
					vpc.getrPropulsaoDado2().setText(mpc.getDado2Mola());
					vpc.getCtPropulsaoDado2().setEnabled(true);
					//Caixas de sele��o das simula��es
						//Teste l�gico para n�o remover linha do LO
						if(vpc.getCsAmbienteSimulacao().getItemCount()==6)
							vpc.getCsAmbienteSimulacao().removeItemAt(vpc.getCsAmbienteSimulacao().getItemCount()-1);//Remove a op��o de lan�amento obl�quo
						//Teste l�gico para n�o remover linha do LO||QL
						if(vpc.getCsAmbienteSimulacao().getItemCount()==5)
							vpc.getCsAmbienteSimulacao().removeItemAt(vpc.getCsAmbienteSimulacao().getItemCount()-1);//Remove a op��o de lan�amento obl�quo
					//Painel de F�rmulas
					vpf.getAtVInicial().setText(ModeloPainelFormulas.propMola);
					//Painel de Informa��es
					vpi.setVisivelMola();
					//Altera a imagem
					mP.trocaImagemProp(false);
					vPS.getVisaoPropulsao().setImagemPropulsao(mP.getImagemPropulsao());
					//Defazendo as edi��es espec�ficas da QL 
					vPS.getVisaoPropulsao().setVisible(true);
					vpc.getCsAmbienteSimulacao().setEnabled(true);
					//M�todo necess�rio para desfazer a corre��o o campo de dado 1 da propuls�o por canh�o
					ajustesPConfig();
				}
				//Queda Livre
				else if(vpc.getCsPropulsao().getSelectedItem().equals(mpc.getQL())){
					//Retira as propuls�es normais
					vPS.getVisaoPropulsao().setVisible(false);
					//Insere a op��o QL na caixa de sele��o da simula��o
					if(vpc.getCsAmbienteSimulacao().getItemCount()==5){//Canh�o
						vpc.getCsAmbienteSimulacao().addItem(mpc.getQL());
					}else if(vpc.getCsAmbienteSimulacao().getItemCount()==4){//Mola
						vpc.getCsAmbienteSimulacao().insertItemAt(mpc.getLancamentoObliquo(), 4);
						vpc.getCsAmbienteSimulacao().addItem(mpc.getQL());
					}
					//BLoqueia a caixa de simula��o
					vpc.getCsAmbienteSimulacao().setSelectedIndex(vpc.getCsAmbienteSimulacao().getItemCount()-1);
					vpc.getCsAmbienteSimulacao().setEnabled(false);
					//Troca dos r�tulos e Campos de Texto
						vpc.getrPropulsaoDado1().setText(mpc.getDado1QL());
						vpc.getrPropulsaoDado2().setText(mpc.getDado2QL());
						vpc.getCtPropulsaoDado1().setText("1000");
						vpc.getCtPropulsaoDado2().setText("0");
						vpc.getCtPropulsaoDado2().setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(null,
						"Falha catastr�fica no funcionamento do programa", 
						"Aviso", JOptionPane.WARNING_MESSAGE, null);
				}
				cOBAC.repinta();
			}
		});
	}
	
	//Atualiza��o do r�tulo da barra do Coeficiente de restitui��o
	private void acaoCoefDeRestituicao(){
		vpc.getdObjetoCoeficienteRestituicao().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vpc.getrObjetoAtualCoefRest().setText(""+(vpc.getdObjetoCoeficienteRestituicao().getValue()/100.0));
				mA.getmO().setCoefRestituicao(vpc.getdObjetoCoeficienteRestituicao().getValue()/100.0);
				//O valor do Deslizante (JSlider) � pego e dividido por 100
				//Em seguida � passado para um r�tulo (JLabel),
				//a fim do usu�rio saber o valor que a simula��o usar�
			}
		});
	}
	
	private void acaoExibirColisao(){		
		vpc.getBoColisaoNao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vPS.getVisaoObstaculo().setVisible(false);
				com.setListener(false);
				vpi.setVisivelColisao(false);
				cOBAC.repinta();
			}
		});
		vpc.getBoColisaoSim().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vPS.getVisaoObstaculo().setVisible(true);
				com.setListener(true);
				vpi.setVisivelColisao(true);
				cOBAC.repinta();
			}
		});
	}
	private String cacheTexto1 = "0";
	private String cacheTexto2 = "0";
	//S�rie de ajustes feitos no Painel de Configura��o durante a sele��o de simula��es
		public void ajustesPConfig(){
			//1� - Configura o segundo campo de dados referentes a propuls�o no painel de configura��o
			//-----para que so possa ter seu valor editado caso seja a simula��o de lan�amento obl�quo.
				cacheTexto1 = vpc.getCtPropulsaoDado1().getText();
				cacheTexto2 = vpc.getCtPropulsaoDado2().getText();
				if (vpc.getCsAmbienteSimulacao().getSelectedIndex()!=4 
					&& vpc.getCsPropulsao().getSelectedIndex()==0)
				{//Inicio IF/ELSE 1
					vpc.getCtPropulsaoDado1().setText("0");
					vpc.getCtPropulsaoDado1().setEnabled(false);
					vpc.getCtPropulsaoDado2().setText(cacheTexto2);
				}
				else {
					vpc.getCtPropulsaoDado1().setText(cacheTexto1);
					vpc.getCtPropulsaoDado2().setText(cacheTexto2);
					vpc.getCtPropulsaoDado1().setEnabled(true);
				}
			//Fim 1�
			
			//2� - (Des)Abilita��o do obst�culo de acordo com o tipo de simula��o
				if (vpc.getCsAmbienteSimulacao().getSelectedIndex()==0//Plano
					||vpc.getCsAmbienteSimulacao().getSelectedIndex()==1//Subida
					||vpc.getCsAmbienteSimulacao().getSelectedIndex()==2//Descida
				){
					vpc.getBoColisaoNao().setSelected(vpc.getBoColisaoNao().isSelected());
					segundoAjuste(true);
				}
				else {//Simula��es que n�o suportam a colis�o: P&P, QL e LO
					vpc.getBoColisaoNao().setSelected(true);
					segundoAjuste(false);
				}
			//Fim 2�
		}
		//M�todos usados por ajustesPConfig() para evitar a redund�ncia e c�digo
		private void segundoAjuste(boolean enabled){
			//Intera��o com os bot�es de op��o da colis�o
			vpc.getBoColisaoNao().setEnabled(enabled);
			vpc.getBoColisaoSim().setEnabled(enabled);
		} 
		private void terceiroAjuste(boolean enabled){
			//Intera��o dos Campos de Texto referentes aos dados
			vpc.getCtPropulsaoDado1().setEnabled(enabled);
			vpc.getCtPropulsaoDado2().setEnabled(enabled);
			//Visibilidade do painel de propuls�o
			vPS.getVisaoPropulsao().setVisible(enabled);
		}
		

	//M�todo usado na execu��o para que a intera��o com os componentes seja removida
	public void desativaComponentes(boolean ativado){
		
		//Ajustes de acordo co a simula��o
		if(vpc.getCsPropulsao().getSelectedIndex()==0
		   && vpc.getCsAmbienteSimulacao().getSelectedIndex()!=4){
			vpc.getCtPropulsaoDado1().setEnabled(true);
			if(ativado){vpc.getCtPropulsaoDado1().setText("0");}
		}
		else if(vpc.getCsPropulsao().getSelectedIndex()==2){
			vpc.getCtPropulsaoDado2().setEnabled(true);
			if(ativado){vpc.getCtPropulsaoDado2().setText("0");}
		}
		else{
			vpc.getCtPropulsaoDado1().setEnabled(true);
			if(ativado){vpc.getCtPropulsaoDado1().setText("");}
		}
		
		vpc.getCtPropulsaoDado1().setEnabled(ativado);
		vpc.getCtPropulsaoDado2().setEnabled(ativado);
		vpc.getCsAmbienteSimulacao().setEnabled(ativado);
		vpc.getCsAmbienteAtrito().setEnabled(ativado);
		vpc.getCsAmbienteGravidade().setEnabled(ativado);
		vpc.getCtObjetoMassa().setEnabled(ativado);
		vpc.getdObjetoCoeficienteRestituicao().setEnabled(ativado);
		vpc.getBoColisaoSim().setEnabled(ativado);
		vpc.getBoColisaoNao().setEnabled(ativado);
		vpc.getBaNovaSimulacao().setVisible(!ativado);
	}
}
