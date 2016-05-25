package MateriaPrimaPk;



public class MateriaPrima extends java.lang.Object implements java.lang.Cloneable{
	
	public State state;
	public Integer nome;
	public Integer tipo;
	public Integer descricao;
	public Integer unidade;

	public AdapterInterface adapter;
	public AdapterInterfaceDados dados;

	
	public String nomeOk;
	public String tipoOk;
	public String descricaoOk;
	public String unidadeOk;
	
	
	
	public MateriaPrima()
	{
	state = State.mostrando;
	adapter = new MateriaPrimaPageAdapter();
	dados = new Dados();
	}
	
	public MateriaPrima clone()
	{
		try {
			return (MateriaPrima)super.clone();
		}
		catch(Exception e)
		{
			e.printStackTrace(java.lang.System.err);
		}
		return null;
	}
	
	public void handleEvent(Object... in_colObject)
	{
	
		
		if(in_colObject.length > 0)
		{
			String sEventName = (String)in_colObject[0];
			
			if((state == State.mostrando) && (sEventName.compareTo("cadastrarEvent") == 0))
			{
				
				nome      = ((Integer)in_colObject[1]).intValue();
				tipo      = ((Integer)in_colObject[2]).intValue();
				descricao = ((Integer)in_colObject[3]).intValue();
				unidade   = ((Integer)in_colObject[4]).intValue();
				
				nomeOk      = dados.dado_tipo(nome);
				tipoOk      = dados.dado_tipo(tipo);
				descricaoOk = dados.dado_descricao(descricao);
				unidadeOk   = dados.dado_unidade(unidade);
				
				if(cadastrar(nomeOk, tipoOk, descricaoOk, unidadeOk))
					 state = State.cadastrando;
				else
					 state = State.mostrando;
			}
			
			
			
			if((state == State.mostrando) && (sEventName.compareTo("alterarEvent") == 0))
			{
				
				nome      = ((Integer)in_colObject[1]).intValue();
				tipo      = ((Integer)in_colObject[2]).intValue();
				descricao = ((Integer)in_colObject[3]).intValue();
				unidade   = ((Integer)in_colObject[4]).intValue();
				
				nomeOk      = dados.dado_nome(nome);
				tipoOk      = dados.dado_tipo(tipo);
				descricaoOk = dados.dado_descricao(descricao);
				unidadeOk   = dados.dado_unidade(unidade);
				
				if(alterar(nomeOk, tipoOk, descricaoOk, unidadeOk))
					 state = State.alterando;
				else
					 state = State.mostrando;
			}
			
			
			if((state == State.cadastrando) && (sEventName.compareTo("fazerCadastroEvent") == 0))
			{
				if(fazerCadastro() && nome>0)
					 state = State.mostrando;
				else
					 state = State.cadastrando;
			}
			
			if((state == State.alterando) && (sEventName.compareTo("fazerAlteracoesEvent") == 0))
			{
				if(fazerAlteracoes() && nome>0)
					 state = State.mostrando;
				else
					 state = State.alterando;
			}
			
			
		}
	}
	
	public Boolean cadastrar(String nome, String tipo, String descricao, String unidade)
	{
	  	//return true;
	  	return adapter.cadastrarEvent(nome, tipo, descricao, unidade);
	}
	

	public Boolean fazerCadastro()
	{
	  	//return true;
	  	return adapter.fazerCadastroEvent();
	}
	

	public Boolean alterar(String nome, String tipo, String descricao, String unidade)
	{
	  	//return true;
	  	return adapter.alterarEvent(nome, tipo, descricao, unidade);
	}
	

	public Boolean fazerAlteracoes()
	{
	  	//return true;
	  	return adapter.fazerAlteracoesEvent();
	}
	
	
	
}
