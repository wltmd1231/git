package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.NotEnoughBalanceException;
import lombok.Data;

@Data
public class Player {

    private int balance;
    private int currentBet;
    private Hand hand;

    public Player(int seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;
    }

    public void placeBet(int bet) {
        if(balance < bet) {
            throw new NotEnoughBalanceException();
        }
        balance -= bet;
        currentBet = bet;
    }

    public Card hitCard() {
        return hand.drawCard();
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public Hand getHand() {
        return hand;
    }
}
