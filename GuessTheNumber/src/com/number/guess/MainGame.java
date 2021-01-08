package com.number.guess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class MainGame {
    private JTextField playerAnswer;
    private JButton btnGuess;
    private JButton btnReset;
    private JButton btnGiveUp;
    private JPanel gamePanel;
    private JLabel labelScore;
    private JLabel yourScore;
    private JLabel labelTentativas;
    private JLabel labelFailures;
    private JLabel labelSetAnswer;
    private JButton btnResetRandom;

    //int valores
    int successCounter = 0;
    int failureCounter = 0;
    int attempts = 15; //tentativas
    int i = 0; // se tried chegar a 0, acaba.
    Random rand = new Random();
    int randomNumber = rand.nextInt(100) + 1;

    public MainGame() {

        btnGuess.addActionListener(e -> {
            //iniciar JText de resposta
            int playerInput = Integer.parseInt(playerAnswer.getText());

            //Se a resposta estiver correta vai desativar o botao e piscar o Try Again.
            if (playerInput == randomNumber) {
                successCounter++;
                labelScore.setText(String.valueOf(successCounter));
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed it!.\n The number was: " + randomNumber);
                JOptionPane.showMessageDialog(null, "Restart = restart the game (duh)\n" +
                        "Generate random = Creates another number to guess.",
                        "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                btnGuess.setEnabled(false);
                playerAnswer.setEnabled(false);
               // btnGuess.setEnabled(false);
                BlinkTry();
                BlinkResetRandom();
            //Se o numero aleatório for menor que a resposta, da uma dica.
            } else if (randomNumber < playerInput) {
                failureCounter++;
                attempts = attempts -1;
                labelSetAnswer.setText("The number is smaller than " + playerInput);
                labelFailures.setText(String.valueOf(failureCounter));
            //Se o numero aleatório for maior que a resposta, da uma dica.
            } else if (randomNumber > playerInput) {
                failureCounter++;
                attempts = attempts -1;
                labelSetAnswer.setText("The number is bigger than " + playerInput);
                labelFailures.setText(String.valueOf(failureCounter));

            }

            //Se o número de tentativas for igual a i (0), diz que perdeu
            //ainda não funciona.
            if (attempts == i) {
                JOptionPane.showMessageDialog(null, "You lost..", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                btnGuess.setEnabled(false);
                btnResetRandom.setEnabled(false);
                playerAnswer.setEnabled(false);
                BlinkTry();
            }


        }); //botão de adivinha. 5 tentativas.

        btnGiveUp.addActionListener(e -> {
            //Fechar programa
            JOptionPane.showMessageDialog(null, "Closing the game.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }); //botão de desistir. Fecha o jogo

        btnReset.addActionListener(e -> resetGame());  // resetar o jogo, incluindo pontos.

        btnResetRandom.addActionListener(e -> {
            playerAnswer.setText("");
            continueGame();
        }); //resetar apenas o random e ativar o botão Guess.
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "GUESS THE RANDOM NUMBER BETWEEN 1 AND 100!\n" +
                "YOU HAVE 15 ATTEMPTS!", "Game: Guess the number", JOptionPane.INFORMATION_MESSAGE);
        JFrame frame = new JFrame();


        //abrir JFrame
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new MainGame().gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


}



    public void BlinkTry(){
        //Pisca o botão tryAgain em vermelho.
        Timer tryAgainBlink = new Timer(300, new ActionListener() {
            private int counter = 0;
            private boolean on = false;

            public void actionPerformed(ActionEvent e) {
                int timerCounter = 6;
                if (counter >= timerCounter) {
                    btnReset.setBackground(null);
                    ((Timer) e.getSource()).stop();
                } else {
                    btnReset.setBackground( on ? Color.RED : null);
                    on = !on;
                    counter++;
                }
            }
        });
        tryAgainBlink.start();

    } //Fazer o botão Resetar Jogo piscar.

    public void BlinkResetRandom(){
        //Pisca o botão tryAgain em vermelho.
        Timer resetRandom = new Timer(300, new ActionListener() {
            private int counter = 0;
            private boolean on = false;

            public void actionPerformed(ActionEvent e) {
                int timerCounter = 6;
                if (counter >= timerCounter) {
                    btnResetRandom.setBackground(null);
                    ((Timer) e.getSource()).stop();
                } else {
                    btnResetRandom.setBackground( on ? Color.GREEN : null);
                    on = !on;
                    counter++;
                }
            }
        });
        resetRandom.start();

    } //Fazer o botão Resetar Random piscar.

    public void resetGame() {

        randomNumber = rand.nextInt(100) + 1;
        i = 0;
        failureCounter = 0;
        successCounter = 0;
        labelFailures.setText(" ");
        labelSetAnswer.setText(" ");
        labelScore.setText(" ");
        playerAnswer.setText(" ");
        playerAnswer.setEnabled(true);
        btnGuess.setEnabled(true);
        btnResetRandom.setEnabled(true);

    } //Resetar jogo (incluindo pontos)

    public void continueGame(){
        btnGuess.setEnabled(true);
        randomNumber = rand.nextInt(100) + 1;
        labelSetAnswer.setText(" ");
        playerAnswer.setEnabled(true);
    } //Continuar jogo (resetar apenas o botão e o random)


}
