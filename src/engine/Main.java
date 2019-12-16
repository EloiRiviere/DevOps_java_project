/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Benjamin
 */
public class Main {
    
    public static void main(String[] args) {
        Triplon t1 = calcPoint(lancer());
        Triplon t2 = calcPoint(lancer());
        Triplon t3 = calcPoint(lancer());
        Triplon t4 = calcPoint(lancer());
        Triplon t5 = calcPoint(lancer());
        Triplon t6 = calcPoint(lancer());
        Triplon t7 = calcPoint(lancer());
        Triplon t8 = calcPoint(lancer());
    }
    
    public static Triplon calcPoint(List<Integer> liste){
        Triplon lancer = new Triplon(liste);
        lancer.process();
        System.out.println(lancer);
        return lancer;
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
