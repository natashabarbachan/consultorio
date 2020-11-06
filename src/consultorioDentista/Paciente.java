package consultorioDentista;

public class Paciente extends Pessoa {
	
	public void cadastrarPaciente(String nome, long cpf, String endereco ) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
	}
	
	public String toString() {
		return ("Nome: " + getNome() + ", CPF: " + getCpf() + ", Endereço: " + getEndereco());
	}

}
