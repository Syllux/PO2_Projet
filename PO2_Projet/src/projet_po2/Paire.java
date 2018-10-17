/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_po2;

public class Paire<T> {

    private T premier, second;

    public Paire(T premier, T second) {
        this.premier = premier;
        this.second = second;
    }

    public String toString() {
        return "(" + premier.toString() + "," + second.toString() + ")";
    }

    public T getPremier() {
        return this.premier;
    }

    public T getSecond() {
        return this.second;
    }

    public void setPremier(T premier) {
        this.premier = premier;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
