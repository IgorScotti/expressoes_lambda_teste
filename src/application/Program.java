package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.funcionario;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String caminho = "C://temp//in.txt";
		List<funcionario> lista = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

			String linha = br.readLine();
			while (linha != null) {
				String[] campo = linha.split(",");

				lista.add(new funcionario(campo[0], campo[1], Double.parseDouble(campo[2])));
				linha = br.readLine();

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
		
		System.out.print("Informe o salario: ");
		double salario = sc.nextDouble();
		
		List<String> listaEmail = lista.stream()
				.filter(x -> x.getSalario() > salario)
				.map(p -> p.getEmail()).sorted(comp.reversed())//PRA USAR O REVESED DEVE TER UM OBJETO DO TIPO COMPARATOR\\
				
				.collect(Collectors.toList());
		
		listaEmail.forEach(System.out::println);
		
		System.out.println("-----------------------------------");
		System.out.println("Nome dos Funcionarios com a Letra inicial M");
		
		double listaNomeM = lista.stream()
				.filter(x-> x.getNome().charAt(0) == 'M')
				.map(p -> p.getSalario())//USADO PRA FAZER UMA FUNÇÃO DENTRO DA LISTA COMO BUSCAR UM ELEMENTO\\
				.reduce(0.0,(x,y)-> x+y);//O REDUCE ELE É USADO PRA FAZER SOMA DE TODOS OS ELEMENTOS \\
		
		System.out.println(listaNomeM);
	}
}
