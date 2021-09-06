package com.company;
import java.util.Random;
public class Main {

    public static int roundNumber = 1;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static String[] heroesAttakType ={
            "Physical","Megical","Kinetic", "Medic"};

    public static int[]heroesHealth ={260,270,250,300};
    public static int[]heroesDamage ={15,20,25,0};
    public static int hill= 30;


    public static void main(String[]args){
        printStatics();
        while (!isGameFinished()){
            round();

        }

    }
    public static void chooseBossDefence(){
        Random random = new Random();
        int randomindex =
     random.nextInt(heroesAttakType.length);// 0,1,2
     bossDefence = heroesAttakType[randomindex];
        System.out.println("Boss choos " + bossDefence);

    }
    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;

        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;

            }

        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
    public static void medicHill(){
        for (int i = 0; i <3 ; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[3]>0 && heroesHealth[i] <100 ){
                heroesHealth[i]=heroesHealth[i]+hill;

            }

        }
    }



    public static void round(){
        System.out.println("ROUND" + roundNumber);
        chooseBossDefence();
        bossHits();
        medicHill();
        HeroesHits();
        printStatics();
        roundNumber++;

    }
    public static void HeroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttakType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11);//0,1,2,3,4,5,6,7,8
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;

                    }
                    System.out.println("Critical Damage" + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;

                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }

                }

            }
        }

    }

    public static void bossHits() {
        for (int i = 0; i <heroesHealth.length ; i++) {
            if (heroesHealth[i]>0){
                if (heroesHealth[i] - bossDamage <0){
                    heroesHealth[i] = 0;
                }else {
                    heroesHealth[i]= heroesHealth[i]-bossDamage;
                }
            }

        }
    }
    public static void printStatics(){
        System.out.println("___________");
        System.out.println("Boss Health:" + bossHealth +
                 "; Boss Damage:" + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(
            heroesAttakType[i]+"Health:"
                    +heroesHealth[i] +
                    "; " + heroesAttakType[i]+"Damage:"
                    +heroesDamage[i]);
        }
        System.out.println("___________");

    }
}
