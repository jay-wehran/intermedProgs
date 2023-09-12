public class InvalidDateTimeException extends Exception{
    /**
     * no-arh constructor
     */
    public InvalidDateTimeException(){
        super("Invalid Date Format.");
    }
    /**
     * one-arg constructor
     * @param message
     */
    public InvalidDateTimeException(String message){
        super(message);
    } 
}
