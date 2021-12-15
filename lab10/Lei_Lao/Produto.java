package Lei_Lao;

import java.util.ArrayList;
import java.util.List;

public class Produto {

    private int unique_key;
    private String description;
    private double initial_price;
    private double max_bidding;
    private State state;
    private List<Observer> observersList = new ArrayList<>();
    private Observer highestBidder;

    public Produto(String description, double initial_price) {
        this.description = description;
        this.initial_price = initial_price;
        this.state = State.STOCK;
    }

    public void attach(Observer o) {
        observersList.add(o);
    }

    public void dettach(Observer o) {
        observersList.remove(o);
    }

    public boolean registerBidding(Observer observer, double value) {
        if (state == State.LEILAO) {
            if (value > max_bidding) {
                max_bidding = value;
                observersList.add(observer);
                setHighestBidder(observer);
                notifyObservers(value, observer.getName());
                return true;
            } else {
                System.out.println();
            }
            for (Observer ob : observersList) {
                if (ob.getClass().getName().equals("Gestor")) {
                    ob.update(value, this, observer.getName());
                }
            }
            return false;
        }
        return false;
    }

    public void setHighestBidder(Observer bidder){
        this.highestBidder = bidder;
    }
    public Observer getHighestBidder(){
        return this.highestBidder;
    }
    public Double getHighestBid(){
        return this.max_bidding;
    }

    public State getState() {
        return state;
    }

    public void setState(State st, double value) {
        state = st;
        notifyObservers(value, "");
    }

    public void setKey(int key) {
        this.unique_key = key;
    }

    private void notifyObservers(double value, String name) {
        for (Observer observer : observersList) {
            observer.update(value, this, name);
        }
    }

    public void start() {
        state = State.LEILAO;
    }

    // this functions are here just to the IDE
    // stop telling me that i don't use the variables
    public int getUnique_key() {
        return unique_key;
    }

    public String getDescription() {
        return description;
    }

    public double getInitial_price() {
        return initial_price;
    }
}