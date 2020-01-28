/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Benjamin
 */
public class Triplon {

    private List<Integer> des;
    private int points;
    private String type;

    public Triplon() {
        this.des = lancer();
    }

    public List<Integer> getDes() {
        return des;
    }

    public void setDes(List<Integer> des) {
        this.des = des;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean process() {
        if (this.des.size() == 3) {
            Collections.sort(this.des);

            //On vérifie si c'est un cul de chouette = 3 chiffres pareils
            if (Objects.equals(this.des.get(0), this.des.get(1)) && Objects.equals(this.des.get(1), this.des.get(2))) {
                this.setType("Cul de chouette");
                switch (this.des.get(0)) {
                    case 1:
                        this.setPoints(50);
                        break;
                    case 2:
                        this.setPoints(60);
                        break;
                    case 3:
                        this.setPoints(70);
                        break;
                    case 4:
                        this.setPoints(80);
                        break;
                    case 5:
                        this.setPoints(90);
                        break;
                    case 6:
                        this.setPoints(100);
                        break;
                }
                return true;
            }
            
            //On vérifie si c'est une chouette = 2 chiffres pareils
            //Ou si c'est une chouette velute
            if (Objects.equals(this.des.get(0), this.des.get(1)) || Objects.equals(this.des.get(1), this.des.get(2)) || Objects.equals(this.des.get(2), this.des.get(0))) {
                if (Objects.equals(this.des.get(0), this.des.get(1))) {
                    if (Objects.equals(this.des.get(0) * 2, this.des.get(2))) {
                        this.setType("Chouette velute");
                        this.setPoints(2 * (this.des.get(0) * this.des.get(0)));
                        return true;
                    } else {
                        this.setType("Chouette de " + this.des.get(0));
                        this.setPoints(this.des.get(0) * this.des.get(0));
                        return true;
                    }
                } else if (Objects.equals(this.des.get(1), this.des.get(2))) {
                    if (Objects.equals(this.des.get(1) * 2, this.des.get(0))) {
                        this.setType("Chouette velute");
                        this.setPoints(2 * (this.des.get(1) * this.des.get(1)));
                        return true;
                    } else {
                        this.setType("Chouette de " + this.des.get(1));
                        this.setPoints(this.des.get(1) * this.des.get(1));
                        return true;
                    }
                } else if (Objects.equals(this.des.get(2), this.des.get(0))) {
                    if (Objects.equals(this.des.get(2) * 2, this.des.get(1))) {
                        this.setType("Chouette velute");
                        this.setPoints(2 * (this.des.get(2) * this.des.get(1)));
                        return true;
                    } else {
                        this.setType("Chouette de " + this.des.get(2));
                        this.setPoints(this.des.get(2) * this.des.get(2));
                        return true;
                    }
                }
            }

            //On vérifie si c'est une velute = somme de deux dés égale au 3eme dé
            if (Objects.equals(this.des.get(0) + this.des.get(1), this.des.get(2))) {
                this.setType("Velute de " + this.des.get(2));
                this.setPoints(this.des.get(2) * this.des.get(2) * 2);
                return true;
            }

            //On vérifie si c'est une suite
            if (Objects.equals(this.des.get(0), this.des.get(1) - 1) && Objects.equals(this.des.get(1), this.des.get(2) - 1)) {
                this.setType("Suite");
                return true;
            }
            
            //Si il n'y a aucune combinaison
            this.setType("Néant");
            return true;

        } else {
            System.out.println("Il n'y a le bon nombre de lancer");
            return false;
        }
    }

    @Override
    public String toString() {
        return "lance les dés : " + des + ". "+ type + " (" + points + ")";
    }
    
    public static List<Integer> lancer(){
        List<Integer> lancer = new ArrayList<>();
        Random r = new Random();
        lancer.add(r.nextInt(6) + 1);
        lancer.add(r.nextInt(6) + 1);
        lancer.add(r.nextInt(6) + 1);
        return lancer;
    }

}
