/***
 * 
 * Author: Unknown
 * Date modified: June 22, 2023
 * Modified By: Jason Wehran
 */
public class Time implements Comparable<Time>{
    private int hours;
    private int minutes;

    /**
     * No-Arg contructor
     */
    public Time(){
        hours = minutes = 0;
    }

    /**
     * one-arg constructur
     * @param time
     * @throws InvalidDateTimeException
     */
    public Time(String time) throws InvalidDateTimeException{
        String[] items = time.split(":");
        try{
            int h = Integer.parseInt(items[0]);
            int m = Integer.parseInt(items[1]);
            if(h < 0 || h > 23)
                throw new InvalidDateTimeException("Invalid hours. Hours should be from 0 to 23.");
            if(m < 0 || m > 59)
                throw new InvalidDateTimeException("Invalid minutes. Minutes should be from 0 to 59.");
            hours = h;
            minutes = m;
        }
        catch(NumberFormatException e){
            System.out.println("Error: hours or minutes are not numbers.");
        }
    }
    /**
     * Getter for hours
     * @return
     */
    public int getHours(){
        return hours;
    }
    /**
     * Getter for minutes
     * @return
     */
    public int getMinutes(){
        return minutes;
    }

    /**
     * Setter for hours
     * @param h
     * @throws InvalidDateTimeException
     */
    public void setHours(int h) throws InvalidDateTimeException{
        if(h < 0 || h > 23)
                throw new InvalidDateTimeException("Invalid hours. Hours should be from 0 to 23.");
        hours = h;
    }
    /**
     * Setter for minutes
     * @param m
     * @throws InvalidDateTimeException
     */
    public void setMinutes(int m) throws InvalidDateTimeException{
        if(m < 0 || m > 59)
                throw new InvalidDateTimeException("Invalid minutes. Minutes should be from 0 to 59.");
        minutes = m;
    }
    /**
     * Returns String representation of time object
     */
    public String toString(){
        return String.format("%02d:%02d", hours, minutes);
    }
    /**
     * Compares two time objects
     */
    public int compareTo(Time time){
        if(this.getHours() == time.getHours()){
            return this.getMinutes() - time.getMinutes();
        }
        return this.getHours() - time.getHours();
    }
    /**
     * Checks to see if two times are equal
     */
    public boolean equals(Object obj){
        if(obj instanceof Time){
            Time time = (Time) obj;
            return this.hours == time.hours && 
                   this.minutes == time.minutes;
        }
        return false;
    }

    /**
     * Method to find difference in time
     * @param t
     * @return
     */
    public int diff(Time t) {
        int minutes1 = hours * 60 + minutes;
        int minutes2 = t.hours * 60 + t.minutes;
        return minutes2 - minutes1;
    }

    /**
     * Method to increment up time if reaches hour or day
     */
    public void tick() {
        minutes++;
        if (minutes == 60) {
            hours++;
            minutes = 0;
        }
        if (hours == 24) {
            hours = 0;
        }
    }
}