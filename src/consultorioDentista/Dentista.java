package consultorioDentista;

public class Dentista extends Pessoa {

	private int CRO;
	
	public Dentista(String nome, long cpf, String endereco, int CRO) {
		super(nome, cpf, endereco);
		this.CRO = CRO;
	}

	public int getCRO() {
		return CRO;
	}

	public void setCRO(int CRO) {
		this.CRO = CRO;
	}
}
