package br.uam.criptografialocalrsa;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Servidor iniciado. Aguardando conexão...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado!");

            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            RSA rsa = new RSA(new BigInteger("17"), new BigInteger("23")); // Modifique os valores conforme necessário

            String mensagemRecebida = input.readUTF();
            System.out.println("Mensagem recebida do cliente: " + mensagemRecebida);

            String mensagemCifrada = rsa.encrypt(mensagemRecebida);
            System.out.println("Mensagem cifrada: " + mensagemCifrada);

            output.writeUTF(mensagemCifrada);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
