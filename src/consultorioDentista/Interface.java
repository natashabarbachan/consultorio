package consultorioDentista;

import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Interface {
	
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	Scanner meuLeitor = new Scanner(System.in);
	Date dataAtual = new Date();
	Dentista dentista = new Dentista(
			"Dra. Nathália Barbachan",
			1234567891,
			"Avenida dois",
			15923);
	
	List<Paciente> pacientes = new ArrayList<>();
	List<Agendamento> agendamentos = new ArrayList<>();
	
	public void menu() throws ParseException {
		
		System.out.printf("***** Bem vindo ao consultório %s com CRO: %s *****%n%n", dentista.getNome(), dentista.getCRO());
		System.out.println("1. Cadastrar Paciente\n2. Cadastrar Agendamento\n3. Ver Agendamentos do dia \n4. Consultar pacientes \n5. Sair\n");
		
		int opcao = meuLeitor.nextInt();
		
		while(opcao != 5) {
			switch(opcao) {
				case 1: 
					chamarCadastroPaciente();
					menu();
					break;
				case 2:
					chamarCadastroAgendamento();
					menu();
					break;
				case 3:
					imprimeAgendamentosDoDia();
					menu();
					break;
				case 4:
					imprimePacientes();
					menu();
					break;
				default:
					System.out.println("Você digitou algum valor inválido, tente novamente.\n");
					menu();
			}
		}
		System.out.println("Você saiu do sistema!");
		System.exit(0);
	}
	
	private void chamarCadastroPaciente() throws ParseException {
		
		Paciente paciente = new Paciente();
		
		System.out.println("Informe o Nome:");
		meuLeitor.nextLine();
		String nome = meuLeitor.nextLine();

		System.out.println("Informe o CPF:");
		long cpf = meuLeitor.nextLong();
		
		System.out.println("Informe o Endereço:");
		meuLeitor.nextLine();
		String endereco = meuLeitor.nextLine();
		
		paciente.cadastrarPaciente(nome, cpf, endereco);
		System.out.println("Paciente cadastrado com sucesso: " + paciente.toString() + "\n");

		pacientes.add(paciente);
	
	}

	private void imprimePacientes() {
		System.out.println("Lista de Pacientes Cadastrados");
		
		pacientes.forEach(paciente -> {
			System.out.println(paciente.toString());
		});
		
		System.out.println();
	}
	
	private void chamarCadastroAgendamento() throws ParseException {
		
		Agendamento agendamento = new Agendamento();

		System.out.print("Digite o CPF do paciente a ser agendado: ");
		meuLeitor.nextLine();
		long cpf = meuLeitor.nextLong();
		
		Paciente paciente = pacientes.stream().filter(pac -> pac.getCpf() == cpf).findFirst().orElse(null);

		if (paciente != null) {
			System.out.print("Digite a data do agendamento (dd/MM/yyyy): ");
			Date data = formato.parse(meuLeitor.next());
			
//			TODO: Validar que a data de agendamento não deve ser menor que a data atual.
//			if (formato.format(data).equals(formato.format(dataAtual))) {
				
				agendamento.cadastrarAgendamento(paciente, data);
				agendamentos.add(agendamento);
				imprimeConfirmacaoAgendamento(agendamento, paciente);
				
//			} else {
//				System.out.println("Desculpe, data de agendamento não pode ser anterior a data atual\n");
//				chamarCadastroAgendamentoDoDia();
//			}
		} else {
			System.out.println("Paciente não está cadastrado no sistema. Tente novamente.\n");
		}
	
	}

	private void imprimeConfirmacaoAgendamento(Agendamento agendamento, Paciente paciente) {
		System.out.println(
			"\nAgendamento confirmado para " + paciente.getNome()
			+ " na data: " + formato.format(agendamento.getData()
		));	
	}

	private void imprimeAgendamentosDoDia() {
		List<Agendamento> agendaDoDia = agendamentos.stream().filter(agendamento ->
			formato.format(agendamento.getData()).equals(formato.format(dataAtual)))
				.collect(Collectors.toList());
		
		if (agendaDoDia.isEmpty()) {
			System.out.println();
			System.out.println("Não encontramos paciente para hoje.\n");
		} else {
			System.out.println();
			
			agendaDoDia.forEach(agendamento -> {
				System.out.println(agendamento.toString());
			});
			System.out.println();
		}
	}
	
}
