package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private Deck deck;
    private List<Card> cardList = new ArrayList<Card>();
    private int cardSum;

    public void cardInit(){
        cardList.clear();
        cardSum = 0;
    }

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public void convertRank(){
        if(cardList.size()==2) {
            int num1 = cardList.get(0).getRank();
            int num2 = cardList.get(1).getRank();

            //카드의 rank가 10을 넘을 경우 10으로 설정
            if (num1 > 10)
                num1 = 10;
            if (num2 > 10)
                num2 = 10;

            //카드의 rank중 1이 있을 때, 두 카드의 합이 17보다 작으면 1을 11로 설정
            if (num1 == 1 && num2 == 1) {
                cardSum = 12;
            } else if (num1 == 1) {
                if (11 + num2 > 16)
                    cardSum = num1 + num2;
                else cardSum = 11 + num2;
            } else if (num2 == 1) {
                if (11 + num1 > 16)
                    cardSum = num1 + num2;
                else cardSum = 11 + num1;
            } else
                cardSum = num1 + num2;
            //카드가 3개일 때는 2개의 카드에 그냥 더함
        }else if(cardList.size() >= 3) {
            int num = cardList.get(2).getRank();

            if(num > 10)
                num = 10;
            if(num == 1){
                if(cardSum + num > 16){
                    num = 11;
                }else num = 1;
            }
            cardSum += num;
        }
    }

    public int getCardSum(){
        return cardSum;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int curCardNum(){
        return cardList.size();
    }

    public void setCardSum(int num){
        cardSum = num;
    }
}
