package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.PlayerMustHitUnder16;

import java.util.Map;

public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void chk17(){
        playerMap.forEach((name, player) -> {
            while (player.getHand().curCardNum() <= 16) {
                player.hitCard();
            }
        });
    }

    // 플레이어가 모든 카드를 받고 공개하는 상황(배팅금 조정)
    public void start() {
        // 각 플레이어의 배팅금 확인
        // 플레이어의 승패 결정
        // 각 플레이어의 배팅금 이동(패자 - > 승자)
        // 블랙잭으로 승리시, 보상 2배
        int dealerScore = dealer.getDealerScore();
        playerMap.forEach((name, player) -> {
            int bet = player.getCurrentBet();
            int balnc = player.getBalance();

            if(player.getHand().curCardNum() <= 16) {
                System.out.println("Hit");
                throw new PlayerMustHitUnder16();
            }

            if(player.getHand().curCardNum() > dealerScore){
                if(player.getHand().curCardNum() == 21){
                    player.setBalance(bet * 3 + balnc);
                }
                player.setBalance(bet * 2 + balnc);
            }else{
                if(player.getHand().curCardNum() == 21){
                    player.setBalance(balnc - bet);
                }
            }
        });
    }

    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }
}