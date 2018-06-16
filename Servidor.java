import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Servidor {

	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(6660);
        Socket conexaoCliente = server.accept();
        
        System.out.println("Dollars Chat 1.0");
        Scanner in = new Scanner(System.in);
        System.out.print("Informe seu nome de usuário: ");	
        String usuario = new java.util.Scanner(System.in).nextLine();
        
     
	      //Armazenar a matriz.
	        //Abaixo, é criada um Array Bidimensional para armazenar nossa matriz.
			int m[][] = new int[2][2];
			
			// Lê os numero da seguinte forma:
			//Quando o primeiro (for) roda o valor de "u" será 0;
			// No segundo (for) o j tambem tera valor 0 no primeiro ciclo ou seja;
			//O valor sera armazenado em m[0][0];
			// Quando o segundo (for) rodar novamente sera em m[0][1];
			//Voltando ao primeiro (for) tudo se repetira so que "u" valendo 1;
			// Dai sera m[1][0] e m[1][1].
			//Calculo da Determinante.
			//Onde ao final, o valor da determinante ficará armazenada em "d".
           int d = 0;
			
			while (true) {
			
			System.out.print("Insira sua chave de 4 digitos: ");				
			
			for (int u = 0; u < 2; u++) {
			   for (int j = 0; j < 2; j++) {
			        m[u][j] = in.nextInt();
			      }
			    }
			
			d = (m[0][0] * m[1][1]) - (m[0][1] * m[1][0]);
			if(d != 0 && d % 2 != 0) {
				
				break;
				
			}
			System.out.println("Matriz inválida!");
			}
        
        int dory = 1;
        
        while (dory == 1) {
        	
        DataInputStream entrada = new DataInputStream(conexaoCliente.getInputStream());
        int tamanhoUsuario = entrada.readInt();
        byte[] byteUsuario = new byte[tamanhoUsuario];
        entrada.read(byteUsuario);
        int tamanhoMensagem = entrada.readInt();
        int cifrado[] = new int [tamanhoMensagem];
        for(int kiwi = 0; tamanhoMensagem > kiwi; kiwi++) {
        	cifrado[kiwi] = entrada.readInt();
        }
        
        
		
      //Modulo multiplicativo inverso
		int c = 0;
		int x = 0;
		int i = 1;
		
		while (c == 0) {
			x = ((256 * i) + 1)/ d;
			
			if (d * x == (256 * i) + 1 ) {
				c++;
			}else {
				i++;
			}
			
		}
		while (x < 0) {
			x = x + 256;
		}
		
		
		//Invertendo matriz
		int inv[][] = new int [2][2];
		inv[0][0] = m[1][1];
		inv[0][1] = (m[0][1] * (-1));
		inv[1][0] = (m[1][0] * (-1));
		inv[1][1] = m[0][0];
		
    
		int key[][] = new int [2][2];
		key[0][0] = (inv[0][0] * x);
		key[0][1] = (inv[0][1] * x);
		key[1][0] = (inv[1][0] * x);
		key[1][1] = (inv[1][1] * x);
		
		
		int decifrada[] = new int [tamanhoMensagem];
		String msg = "";
		
		for(int pera = 0; pera < tamanhoMensagem; pera++) {
			decifrada[pera] = (key[0][0]*cifrado[pera])+(key[0][1]*cifrado[pera+1]);
			decifrada[pera+1] = (key[1][0]*cifrado[pera])+(key[1][1]*cifrado[pera+1]);
			
			if (decifrada[pera] > 255) {
	    		while (decifrada[pera] > 255) {
	    			decifrada[pera] = decifrada[pera] - 256;
	    		}
	    	}
			
			if (decifrada[pera] < 0) {
	    		while(decifrada[pera] < 0) {
	    			decifrada[pera] = decifrada[pera] + 256;
	    		}
	    	}
			msg = msg +(char)decifrada[pera];
			
			pera++;
			
			if (decifrada[pera] > 255) {
	    		while (decifrada[pera] > 255) {
	    			decifrada[pera] = decifrada[pera] - 256;
	    		}
	    	}
			
			if (decifrada[pera] < 0) {
	    		while(decifrada[pera] < 0) {
	    			decifrada[pera] = decifrada[pera] + 256;
		    		}
	    	}
			
			msg = msg +(char)decifrada[pera];
			
		}
		
        String Usuario = new String(byteUsuario, "UTF-8");
        
        
		System.out.printf("%s: ",Usuario);
	    System.out.println(msg);
	    
	    //Mandar mesensagem!
	    System.out.printf("%s : ",usuario);
        String mensagem = new java.util.Scanner(System.in).nextLine();
        
        
               
        
      	    
	  
						
	//Comparação se a Matriz é valida.
		//A condição para que exista a matriz inversa que será usada na descriptografação é que;
		//a determinante seja diferente de 0;
		
		if (d != 0) {
			String name = mensagem;
			
			
	     // Criar variavel contendo o tamanho do nome	    
			int nameLenght = name.length();
			
			
		 // Caso o numero de letras seja impar, sera adicionado um ponto (.) ao final da frase.
		    if (nameLenght % 2 != 0) {
		    	name = name + ".";
		    	
		    }
		 //Atualizando valor da variavel tamanho de nome
		    nameLenght = name.length();
		    int loli[] = new int[nameLenght];
		    
		    int cipher[] = new int[nameLenght];
		 //Separando os caracteres enquanto converte eles em valor numerico;
		    // Serão armazenados no Array loli[].
		        for(int w = 0; w < nameLenght ; w++){          
		        char character = name.charAt(w); 
		        int charValue = (int) character; 
		        loli[w] = charValue;
		        						        
		    }
		      //Cria o Array "chipher[] para armazenar os numeros apos a criptografia.
		    	
		    	
		    for(int h = 0; h < nameLenght; h++) {
     	// Cifrando os numeros gerados anteriormente.
		    	
		    	
		    	//São feitas as contas onde são utilizadas duas letras ao mesmo tempo na multiplicação das matrizes.
		    	cipher[h] = (m[0][0]*loli[h]) + (m[0][1]*loli[h+1]);
		    	cipher[h+1] = (m[1][0]*loli[h])+ (m[1][1]*loli[h+1]);
		    	
		    	//Agora, comparamos se o valor esta dentro do intervalo 0 < cipher[h] < 256;
		    	//Caso não esteja, fazemos o equivalente a uma multiplicação modular para descobrir sua posição;
		    	//relativa dentro do intervalo.
		    	
		    	
		    	//Caso seja maior que 256, atravez de subtrações sucessivas ele entrara no intervalo.
		    	if (cipher[h] > 256) {
		    		while (cipher[h] > 256) {
		    			cipher[h] = cipher[h] - 256;
		    		}
		    	}
		    	//Caso seja menor que 0, atravez de somas sucessivas ele entrara no intervalo.
		    	if (cipher[h] < 0) {
		    		while(cipher[h] < 0) {
		    			cipher[h] = cipher[h] + 256;
		    		}
		    	}
		    						    	
		    	//Acima, comparamos o cipher[h], porem trabalhamos com duas posições ao mesmo tempo;
		    	//No caso iremos tratar a posição cipher[h+1].
		    	h++;
		    	
		    	//Caso seja maior que 256, atravez de subtrações sucessivas ele entrara no intervalo.
		    	if (cipher[h] > 256) {
		    		while (cipher[h] > 256) {
		    			cipher[h] = cipher[h] - 256;
		    		}
		    	}
		    	
		    	//Caso seja menor que 0, atravez de somas sucessivas ele entrara no intervalo.
		    	if (cipher[h] < 0) {
		    		while(cipher[h] < 0) {
		    			cipher[h] = cipher[h] + 256;
		    		}
		    	}
		    	
		    	
		    }
		    
		    
		    byte[] bytesUsuario = usuario.getBytes("UTF8");
	        
	        DataOutputStream saida = new DataOutputStream(conexaoCliente.getOutputStream());
	        saida.writeInt(bytesUsuario.length);
	        saida.write(bytesUsuario);
	        saida.writeInt(nameLenght);
	        for(int jabulani = 0; jabulani < nameLenght; jabulani++) {
	        	saida.writeInt(cipher[jabulani]);
	        }
	        
	        
}
		}
	}

}
