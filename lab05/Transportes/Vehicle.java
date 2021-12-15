
/*
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

interface Vehicle {

    public int getMaxVolume();
    public int getMaxPassangers();
    
}

class Scooter implements Vehicle{

    private int maxVolume = 0;
    private int maxPassangers = 1;
    private int passengers;

    public Scooter(int passengers) {
        this.passengers = passengers;
    }
    
    @Override
    public int getMaxVolume() {
        return maxVolume;
    }
    
    @Override
    public int getMaxPassangers() {
        return maxPassangers;
    }

    @Override
    public String toString() {
        return "Vehicle for " + passengers + " passenger : Use a Schooter";
    }
}

class Micro implements Vehicle {
    
    private int maxVolume = 250;
    private int maxPassangers = 1;

    private int passengers;
    private int nitems;
    
    public Micro(int passengers, int nitems) {
        this.passengers = passengers;
        this.nitems = nitems;
    }

    @Override
    public int getMaxVolume() {
        return maxVolume;
    }

    @Override
    public int getMaxPassangers() {
        return maxPassangers;
    }

    @Override
    public String toString() {
        return "Vehicle for " + passengers + " passengers with " + nitems + " items of luggage: Use a Micro Car";
    }
}

class City implements Vehicle {
    
    private int maxVolume = 250;
    private int maxPassangers = 3;

    private int passengers;
    private int nitems;
    
    public City(int passengers, int nitems) {
        this.passengers = passengers;
        this.nitems = nitems;
    }

    @Override
    public int getMaxVolume() {
        return maxVolume;
    }

    @Override
    public int getMaxPassangers() {
        return maxPassangers;
    }

    @Override
    public String toString() {
        return "Vehicle for " + passengers + " passengers with " + nitems+ " items of luggage: Use a City Car";
    }
}

class Family implements Vehicle {
    
    private int maxVolume = 600;
    private int maxPassangers = 4;

    private int passengers;
    private int nitems;
    
    public Family(int passengers, int nitems) {
        this.passengers = passengers;
        this.nitems = nitems;
    }
    
    @Override
    public int getMaxVolume() {
        return maxVolume;
    }

    @Override
    public int getMaxPassangers() {
        return maxPassangers;
    }

    @Override
    public String toString() {
        return "Vehicle for " + passengers + " passengers with " + nitems+ " items of luggage: Use a Family Car";
    }
}

class Van implements Vehicle {
    
    private int maxVolume = 1000;
    private int maxPassangers = 4;

    private int passengers;
    private int nitems = 0;
    private boolean op;
    
    public Van(int passengers, int nitems) {
        this.passengers = passengers;
        this.nitems = nitems;
    }

    public Van(int passengers, boolean op) {
        this.passengers = passengers;
        this.op = op;
    }

    public Van(int passengers, int nitems, boolean op) {
        this.passengers = passengers;
        this.nitems = nitems;
        this.op = op;
    }
    
    @Override
    public int getMaxVolume() {
        return maxVolume;
    }

    @Override
    public int getMaxPassangers() {
        return maxPassangers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle for " + passengers);
        if (nitems != 0) {
            sb.append(" with " + nitems + " items of luggage");
            if (op == true) {
                sb.append(" and a wheelchair: Use a Van");
            } else {
                sb.append(": Use a Van");
            }
        } else if (op == true) {
            sb.append(" and a wheelchair: Use a Van");
        }
        return sb.toString();
    }
}