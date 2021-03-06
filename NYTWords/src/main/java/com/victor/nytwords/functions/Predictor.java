/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.victor.nytwords.functions;

import java.util.Collection;
import java.util.HashMap;

/**
 * Predict the next point in a data set
 * @author Victor Rodrigues
 */
public class Predictor {
    private HashMap<Double,Double> points;
    
    public Predictor(HashMap hm){
        this.points = hm;
    }
    
    /**
     * Calculate the average of numbers
     * @param set Numbers considered
     * @return Average of numbers in the set
     */
    public double averageNormal(Collection<Double> set){
        double total = 0;
        int cnt = 0;
        for(Double d : set){
            total += d;
            cnt++;
        }
        return total/cnt;
    }
    
    /**
     * Calculate the average of squared numbers
     * @param set Set of numbers considered
     * @return Returns the average of the square of the numbers in the set
     */
    public double averageSquare(Collection<Double> set){
        double total = 0;
        int cnt = 0;
        for(Double d : set){
            total += d*d;
            cnt++;
        }
        return total/cnt;
    }
    
    /**
     * Calculate the average of multiplication of
     * x coordinate times respective y coordinate
     * @return Returns the average of multiplication of x cord with y cord
     */
    public double averageMultiplication(){
        double total = 0;
        int cnt = 0;
        for(Double d : this.points.keySet()){
            total += d*this.points.get(d);
            cnt++;
        }
        return total/cnt;
    }
    
    /**
     * Calculate angular coefficient (slope) of prediction line 
     * @return Returns angular coefficient of line
     */
    public double angularCoefficient(){
        double avgNX = averageNormal(this.points.keySet());
        double avgNY = averageNormal(this.points.values());
        double avgSX = averageSquare(this.points.keySet());
        double avgMXY = averageMultiplication();
        
        double num = avgNX*avgNY - avgMXY;
        double den = avgNX*avgNX -avgSX;
        
        return num/den;
    }
    
    /**
     * Calculate linear coefficient (value of y when x = 0) 
     * of prediction line
     * @return Returns linear coefficient of line
     */
    public double linearCoefficient(){
        double avgNX = averageNormal(this.points.keySet());
        double avgNY = averageNormal(this.points.values());
        
        double b = avgNY - angularCoefficient()*avgNX;
        
        return b;
    }
    
    /**
     * Find the maximum x value of the data
     * @return Returns maximum x value of data
     */
    public double max(){
        double max = 0;
        int cnt = 0;
        for(double d : this.points.keySet()){
            if(d > max || cnt == 0){
                max = d;
            }
            cnt++;
        }
        return max;
    }
    
    /**
     * Predict y value of next year
     * @return Next y value
     */
    public double predict(){
        return (max()+1)*angularCoefficient()+linearCoefficient();
    }
}
