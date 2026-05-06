package atividadesTrabalhos.exercicios;

public class Principal {
	
	public static void main(String[] args) {
		
		Carro carro = new Carro();
		
		carro.nome = "Song";
		carro.marca = "BYD";
		carro.modelo = "Xing Ling";
		carro.placa = "AZR193B";
		carro.temCarroceria = false;
		carro.temTeto = true;
		carro.tracao = "dianteira";
		carro.ano = 2024;
		
		System.out.println(carro.nome);
		
		Aviao aviao = new Aviao();

		aviao.nome = "371";
		aviao.marca = "Boeing";
		aviao.modelo = "Boeing 371a";
		aviao.altitudeMax = 10000;
		aviao.ano = 2001;
		aviao.numeroMotor = 123456;
		aviao.compania = "TAM";
		System.out.println(aviao.nome);
	}
}