/*
 *  Joshua Lawson, Student and Pursuer of knowledge
 *  Code under no copyrights, free to use
 *  Credit is appreciated
 */
package blackjack;
import java.util.*;

/**
 *
 * @author Josh
 */
public class BlackJack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //game state
        boolean sebek = true;
        Scanner in = new Scanner(System.in);
        Player dealer = new Player();
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        
        int dogCount = 0;
        int carCount = 0;
        int stuffedAnimalCount = 0;
        int wager;
        //two totals used if player chooses to split
        //int splitTotalOne = 0;
        //int splitTotalTwo = 0;
        
        System.out.println("Please enter your name: ");
        String name = in.nextLine();
        Player player = new Player(name);
        
        while(sebek)
            {
            System.out.println("Welcome to the Sebek Casino, "+
            player.getName()+"!"+
            "\nWhat would you like to do?"+
            "\n1.BlackJack"+
            "\n2.Check Balance"+
            "\n3.prizes"+
            "\n4.Quit"        
            );
            int x = in.nextInt();
            
            while(x<0 || x>5)
            {
                System.out.println("Please enter a number in range number");
                x = in.nextInt();
            }
            if(x == 1)
            {
                System.out.println("--------Blackjack--------");
                if(player.getMoney() == 0)
                {
                    System.out.println("You don't have any money, but you can still play for fun");
                    wager = 0;
                }
                else
                {
                    System.out.println("How much would you like to wager?");
                    wager = in.nextInt();
                    while(wager<0 || wager>player.getMoney())
                    {
                        System.out.println("Please enter a valid amount"+
                        "\nYour current balence is "+player.getMoney());
                        wager = in.nextInt();
                    }
                    player.setMoney(player.getMoney()-wager);
                }
                System.out.println("You have wagered " + wager + " Dollars.\nDealers hand:");
                dealer.addCard(deck.deal());
                System.out.println(dealer.hand()+
                "and one face down card"+
                "\n\nYour hand:");
                dealer.addCard(deck.deal());
                int dealerTotal = dealer.getCard(0).getValue()+dealer.getCard(1).getValue();
                player.addCard(deck.deal());
                player.addCard(deck.deal());
                System.out.println(player.hand());
                int playerTotal = player.getCard(0).getValue()+player.getCard(1).getValue();
                System.out.println("Your total: "+ playerTotal+"\n\nWould you like to hit, stay, insure, or double?");
                String input = in.next();

                input = CheckInput(input, dealer);

                if(input.equals("double"))
                {
                    if(wager<=player.getMoney())
                    {
                    player.setMoney(player.getMoney()-wager);
                    wager = wager*2;
                    System.out.println("You have doubled your wager. Your bet is now currently "
                            + wager+ " and your balance is " + player.getMoney() + ". What you like to do next?");
                    input = hitOrStay(input);
                    }
                    else
                    {
                        System.out.println("You're too poor to double. Sorry.");
                        input = hitOrStay(input);
                    }

                }
                if(input.equals("insure"))
                {
                    System.out.println("Sebek Casino's insurance is that you can have your money back"+
                            "and not risk it. To be honest you should stop betting anyways. ");
                    player.setMoney(player.getMoney()+wager);

                }
                if(input.equals("stay"))
                {
                    endGame(dealer, player, wager, deck, dealerTotal, playerTotal);
                }
                while(input.equals("hit"))
                {
                    playerTotal = hit(player, deck, playerTotal);

                    if(playerTotal>=21)
                    {
                        if(player.checkAce())
                        {
                            playerTotal-=10;
                            System.out.println("Your ace has been reduced, your total is now: "+ playerTotal);
                            input = hitOrStay(input);
                        }
                        else
                        {
                            endGame(dealer, player, wager, deck, dealerTotal, playerTotal);
                            input = "stay";
                        }
                    }
                    else
                    {
                        input = hitOrStay(input);
                        if(input.equals("stay"))
                            endGame(dealer, player, wager, deck, dealerTotal, playerTotal);
                    }
                }
                //to be implemented
                /*else if(input.equals("split"))
                {
                    player.addCard(deck.deal());
                    splitTotalOne = player.getCard(0).getValue() + player.getCard(2).getValue();
                    System.out.println("Your First split hand:\n "+
                    player.getCard(0) + "\n" +
                    player.getCard(2) + "\n" +
                    "Your total: " + splitTotalOne);
                    System.out.println("Would you like to hit or stay?");

                    player.addCard(deck.deal());
                    splitTotalTwo = player.getCard(1).getValue() + player.getCard(3).getValue();
                    System.out.println("Your First split hand:\n "+
                    player.getCard(1) + "\n" +
                    player.getCard(3) + "\n" +
                    "Your total: " + splitTotalTwo);
                    System.out.println("Would you like to hit or stay?");

                }*/


        }
        if(x == 2)
        {
            System.out.println(player.getMoney());
        }
        if(x == 3)
        {
            int money = player.getMoney();
            System.out.println("What prize would you like to redeem?"+
            "\n1.Stuffed Animals: 20$"+
            "\n2.Dog: 500$"+
            "\n3.Cars: 20,000$");
             x = in.nextInt();
            while(x<0 || x>3)
            {
                System.out.println("Please enter a number in range number");
                x = in.nextInt();
            }
            if(x == 1)
            {
                stuffedAnimalCount = prize("Stuffed Animal", stuffedAnimalCount, 20, money, player);
            }
            if(x == 2)
            {
                dogCount = prize("dog",dogCount, 500, money, player);
            }
            if(x == 3)
            {
                carCount = prize("car", carCount, 20000, money, player);
            }

            }
            if(x == 4)
            {
                sebek = false;
            }
            if(x == 5)
            {
                System.out.println("Cheat code activated\nMoney set to 5,000,000");
                player.setMoney(5000000);
            }

        }
    }

    private static void endGame(Player Dealer, Player Player, int wager, DeckOfCards Deck, int dealerTotal, int playerTotal) 
    {
       boolean seventeen = false;
       int value = 2;
       System.out.println("The dealers second card was: " + Dealer.getCard(1).toString()+
       "\nwhich makes the dealers total: " + dealerTotal);
       if(playerTotal>21)
       {
           if(Player.checkAce())
           {
               playerTotal-=10;
           }
           else
           {
           System.out.println("sorry, your total is over 21. You lose."+
           "\nYou Lost "+ wager + " dollars");
           }
       }
       
       else if(playerTotal<dealerTotal)
           {
               if(dealerTotal == 21 )
               {
                   System.out.println("The dealer got a blackjack! Better luck next time."+ " You lose "+ wager+ " dollars.");
               }
               else
               {
                    System.out.println("Dealer score is "+ dealerTotal + ".You lose "+ wager + " dollars.");
               }
           }
       else if((playerTotal == dealerTotal)&& dealerTotal>17)
       {
           if(dealerTotal == 21 )
                {
                   System.out.println("Wow! Two Blackjacks."+ " You don't lose anything, but that's pretty neat.");
                   Player.setMoney(Player.getMoney()+wager);
                }
           else
           {
               System.out.println("A push. You don't lose any money.");
               Player.setMoney(Player.getMoney()+wager);
           }
       }
       while(playerTotal > dealerTotal && playerTotal<22 && dealerTotal<17)
       { 
           Dealer.addCard(Deck.deal());
           dealerTotal+=Dealer.getCard(value).getValue();
           System.out.println("The dealer hits, and gets a "+
           Dealer.getCard(value).toString()+"\nwhich makes his total: " + dealerTotal);
           value++;
            if(dealerTotal>=17)
            {
                if(Dealer.checkAce() && dealerTotal>21)
                {
                    dealerTotal-=10;
                    System.out.println("the dealer chooses to reduce his ace");
                }
                else
                {
                    System.out.println("The dealer has stopped due to reaching 17 or above");
                    if(dealerTotal>playerTotal && dealerTotal<=21)
                    {
                        System.out.println("Dealer score is "+ dealerTotal + ".You lose "+ wager + " dollars.");
                    }
                    if(dealerTotal<playerTotal || dealerTotal>21)
                    {
                        System.out.println("Dealer score is "+ dealerTotal + ".You won "+ wager);
                        Player.setMoney(Player.getMoney()+wager*2);
                    }
                    if(playerTotal == dealerTotal)
                    {
                        System.out.println("A push. You don't lose any money.");
                        Player.setMoney(Player.getMoney()+wager);
                    }
                }
            }
            else if(dealerTotal>21)
           {
               if(Dealer.checkAce())
               {
                   dealerTotal-=10;
               }
               else
               {
                    System.out.println("Dealer score is "+ dealerTotal + ".You won "+ wager);
                    Player.setMoney(Player.getMoney()+wager*2);
                    seventeen = true;
               }
           }
           else if(playerTotal<dealerTotal)
           {
               if(dealerTotal == 21 )
               {
                   System.out.println("The dealer got a blackjack! Better luck next time."+ " You lose "+ wager+ " dollars.");
               }
               else
               {    
                   System.out.println("Dealer score is "+ dealerTotal + ".You lose "+ wager + " dollars.");
               }
                seventeen = true;
           }
           else if(playerTotal == dealerTotal)
            {
                if(dealerTotal == 21 )
                     {
                        System.out.println("Wow! Two Blackjacks."+ " You don't lose anything, but that's pretty neat.");
                        Player.setMoney(Player.getMoney()+wager);
                     }
                else
                {
                    System.out.println("A push. You don't lose any money.");
                    Player.setMoney(Player.getMoney()+wager);
                }
                seventeen = true;
            }

           
       }
       clearHands(Player, Dealer);
       
    }
    
    public static int hit(Player player, DeckOfCards deck, int playerTotal)
    {
        player.addCard(deck.deal());
        playerTotal = playerTotal+ player.lastDelt().getValue();
        System.out.println("You drew a "+ player.lastDelt().toString()
                +"\n Your total is now "+ playerTotal);
        return playerTotal;
    }
    

    private static void clearHands(Player player, Player dealer) 
    {
        player.clearHand();
        dealer.clearHand();
    }
    
    private static String CheckInput(String input, Player dealer)
    {
        Scanner in = new Scanner(System.in);
        while(!(input.matches("stay|hit|split|insure|double")) || (!(dealer.getCard(0).getRank().equals("Ace"))&& input.equals("insure")))
                {
                    if(!(dealer.getCard(0).getRank().equals("Ace"))&& input.equals("insure"))
                    {
                        System.out.println("Sorry, but you can't insure if the dealers first card is not an ace. Please choose again:");
                    }
                    else
                        System.out.println("Please input a correct response");
                    input = in.next();                    
                }
        return input;
           
    }

    private static int prize(String prize, int count, int price, int money, Player player) 
    {
        if(money >= price)
        {
            count++;
            if(count>1)
                System.out.println("Wow! You now have " + count +" "+ prize+"s. You must be proud!");
            else
                System.out.println("Wow! You now have 1 " + prize+". You must be proud!");
            player.setMoney(money-price);
        }
        else
        {
            System.out.println("Sorry, you don't have enough money. You could play some blackjack, or maybe 5 of a kind ; )");
        }
        return count;
    }
    private static String hitOrStay(String input)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to hit or stay?");
            input = in.next();
            while(!input.matches("stay|hit"))
            {
                System.out.println("please enter either hit or stay");
                input = in.next();
            }
            return input;
    }
    
}

