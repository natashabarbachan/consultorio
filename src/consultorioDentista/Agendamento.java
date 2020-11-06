package consultorioDentista;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Agendamento {
	
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date data;
	private Paciente paciente;
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void cadastrarAgendamento(Paciente paciente, Date data) {
		setPaciente(paciente);
		setData(data);
	}
	
	public String toString() {
		return ("Paciente " + paciente.getNome() + " agendado no dia " + formato.format(getData()) );
	}
}
