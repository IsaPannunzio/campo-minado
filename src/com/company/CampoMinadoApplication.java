package com.company;

import com.company.model.Tabuleiro;
import com.company.view.TabuleiroConsole;

public class CampoMinadoApplication {

    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(8, 8, 10);
        new TabuleiroConsole(tabuleiro);

    }
}
