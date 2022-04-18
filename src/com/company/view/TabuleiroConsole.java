package com.company.view;

import com.company.exception.ExplosaoException;
import com.company.exception.SairException;
import com.company.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

        private void executarJogo(){
            try {
                boolean continuar = true;

                while(continuar) {
                    cicloDoJogo();

                    System.out.println("Nova partida? (s/n) \n");
                    String resposta = entrada.nextLine();

                    if ("n".equalsIgnoreCase(resposta)) {
                        continuar = false;
                    } else {
                        tabuleiro.reiniciar();
                    }
                }

            }catch (SairException e) {
                System.out.println("Jogo encerrado");
            } finally {
                entrada.close();
            }

        }


    private void cicloDoJogo() {
        try {

            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);

                String digitado = valorDigitado("Digite os valores de (x, y) ou 'sair': ");

                        Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                        digitado = valorDigitado("1 - Abrir ou 2 - (Des)Marcar ");

                        if ("1".equals(digitado)) {
                            tabuleiro.abrir(xy.next(), xy.next());
                        } else if ("2".equals(digitado)) {
                            tabuleiro.alterarMarcacao(xy.next(), xy.next());
                        }
            }

            System.out.println(tabuleiro);
            System.out.println("Você ganhou! \n");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você perdeu! \n");
        }
    }

    private String valorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }

        return digitado;
    }
}

