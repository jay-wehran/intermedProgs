/***
 * 
 * Author: Jason Wehran
 * Date created: June 22, 2023
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Airport {
    public static void main(String[] args) {
        Queue<Flight> landingQueue = new LinkedList<>(); 
        Queue<Flight> takeoffQueue = new LinkedList<>();

        ArrayList<Flight> landingList = new ArrayList<>();
        ArrayList<Flight> takeoffList = new ArrayList<>();

        try {
            readFlights(takeoffList, "takeoff.txt");
            readFlights(landingList, "landing.txt");

            int simulationDuration = 480;
            Time startTime = new Time("12:00");

            int landingTime = 7;
            int takeoffTime = 7;
            int runway = 0;

            // Perform simulation
            for (int i = 0; i < simulationDuration; i++) {
                // Check landingList for flights arriving at current time
                int landingIndex = findLanding(landingList, startTime);
                if (landingIndex != -1) {
                    Flight landingFlight = landingList.get(landingIndex);
                    landingQueue.offer(landingFlight);
                    System.out.println("A landing request has been added to the landing queue at " + startTime);
                }

                // Check takeoffList for flights departing at current time
                int takeoffIndex = findTakeoff(takeoffList, startTime);
                if (takeoffIndex != -1) {
                    Flight takeoffFlight = takeoffList.get(takeoffIndex);
                    takeoffQueue.offer(takeoffFlight);
                    System.out.println("A takeoff request has been added to the takeoff queue at " + startTime);
                }

                if (runway > 0) { // decrease runway if not free
                    runway--;
                }

                if (runway == 0) {
                    if (!landingQueue.isEmpty()) {
                        Flight landingFlight = landingQueue.poll();
                        int waitingTime = startTime.diff(landingFlight.getArrival());
                        System.out.println(landingFlight.getFlight() + "\t" + landingFlight.getDeparture() + "\t"
                                + landingFlight.getArrival() + "\t" + startTime + "\t" + waitingTime);
                        runway = landingTime;
                    }
                    else if (!takeoffQueue.isEmpty()) {
                        Flight takeoffFlight = takeoffQueue.poll();
                        int waitingTime = startTime.diff(takeoffFlight.getDeparture());
                        System.out.println(takeoffFlight.getFlight() + "\t" + takeoffFlight.getDeparture() + "\t"
                                + takeoffFlight.getArrival() + "\t" + startTime + "\t" + waitingTime);
                        runway = takeoffTime;
                    }
                }

                startTime.tick();
            }

            System.out.println("\nLanding Report"); // this format is modelled off the sample outputs given in the assignment
            System.out.println("Flight\t\tDeparture\tArrival\t\tArrived\t\tWaiting time");
            for (Flight flight : landingList) {
                int waitingTime = flight.getArrival().diff(flight.getArrival());
                System.out.println(flight.getFlight() + "\t\t" + flight.getDeparture() + "\t\t" + flight.getArrival()
                        + "\t\t" + flight.getDeparture() + "\t\t" + waitingTime);
            }

            int totalLandingWaitingTime = calculateTotalWaitingTime(landingList);
            double averageLandingWaitingTime = calculateAverageWaitingTime(totalLandingWaitingTime, landingList.size());
            System.out
                    .println("\nAverage waiting time for landing requests: " + averageLandingWaitingTime + " minutes");

            System.out.println("\nTakeoff Report");
            System.out.println("Flight\t\tDeparture\tArrival\t\tDeparted\tWaiting time");
            for (Flight flight : takeoffList) {
                int waitingTime = flight.getDeparture().diff(flight.getDeparture());
                System.out.println(flight.getFlight() + "\t\t" + flight.getDeparture() + "\t\t" + flight.getArrival()
                        + "\t\t" + flight.getDeparture() + "\t\t" + waitingTime);
            }

            int totalTakeoffWaitingTime = calculateTotalWaitingTime(takeoffList);
            double averageTakeoffWaitingTime = calculateAverageWaitingTime(totalTakeoffWaitingTime, takeoffList.size());
            System.out
                    .println("\nAverage waiting time for takeoff requests: " + averageTakeoffWaitingTime + " minutes");

        } catch (InvalidDateTimeException e) {
            System.out.println("There was an issue with the date and time entry");
        }

    }

    /**
     * method reads the file ‘filename’, creates Flight objects and adds them to
     * list
     * 
     * @param list
     * @param filename
     * @throws InvalidDateTimeException
     */
    public static void readFlights(ArrayList<Flight> list, String filename) throws InvalidDateTimeException {
        File file = new File(filename);
        try {
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                String[] attributes = line.split("\\s+");

                String flightNum = attributes[0];
                Time departure = new Time(attributes[1]);
                Time arrival = new Time(attributes[2]);

                Flight flight = null;

                flight = new Flight(flightNum, departure, arrival);
                list.add(flight);

            }
            scnr.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File could not be found");
        }

    }

    /**
     * method looks for the flight that has an arrival time equal to time. It
     * returns the index of the flight if it is found, -1 otherwise.
     * 
     * @param list
     * @param time
     * @return
     */
    public static int findLanding(ArrayList<Flight> list, Time time) {
        for (int i = 0; i < list.size(); i++) { // traversing through list
            Flight flight = list.get(i); // retrieving object at index i in the arrayList
            if (flight.getArrival().equals(time)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * method looks for the flight that has a departure time equal to time. It
     * returns the index of the flight if it is found, -1 otherwise.
     * 
     * @param list
     * @param time
     * @return
     */
    public static int findTakeoff(ArrayList<Flight> list, Time time) {
        for (int i = 0; i < list.size(); i++) {
            Flight flight = list.get(i);
            if (flight.getDeparture().equals(time)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method to return the total waiting time of a flight to then be used in other operations
     * @param list
     * @return
     */
    public static int calculateTotalWaitingTime(ArrayList<Flight> list) {
        int totalWaitingTime = 0;

        for (Flight flight : list) {
            int waitingTime = flight.getArrival().diff(flight.getArrival());
            totalWaitingTime += waitingTime;
        }

        return totalWaitingTime;
    }

    /**
     * Method to calculate the average waiting time as shown in the sample output
     * @param totalWaitingTime
     * @param totalFlights
     * @return
     */
    public static double calculateAverageWaitingTime(int totalWaitingTime, int totalFlights) {
        if (totalFlights == 0) {
            return 0;
        }
        return (double) totalWaitingTime / totalFlights;
    }

    //! I was unfortunately not able to get the time differences to work and I'm really not sure why I've double checked everything and can't seem to find the error in my code
    //! I'll have to look at it and maybe consult some other resources like office hours or even chatgpt to get secondary input
}
