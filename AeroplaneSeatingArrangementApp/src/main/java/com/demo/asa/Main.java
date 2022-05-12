package com.demo.asa;

import com.demo.asa.exceptions.AppServiceException;
import com.demo.asa.service.providers.AppServiceProvider;
import com.demo.asa.service.providers.SeatSection;
import com.demo.asa.utils.ConstantsUtil;
import com.demo.asa.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<SeatSection> sectionList = new ArrayList<>();
        int maxRow = 0;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input total number of SeatSection : ");
            int noOfSeatSection = sc.nextInt();
            if (Utilities.checkInputs(noOfSeatSection)) {
                throw new AppServiceException("Number of seatSection input value should be more than 0");
            }
            int seatRow;
            int seatColumn;
            for (int i = 1; i <= noOfSeatSection; i++) {
                System.out.print("Input total row of SeatSection : " + i + " : ");
                seatRow = sc.nextInt();
                if (Utilities.checkInputs(noOfSeatSection)) {
                    throw new AppServiceException("Number of row input value should be more than 0");
                }
                if (seatRow > maxRow) {
                    maxRow = seatRow;
                }
                System.out.print("Input total row of SeatSection : " + i + " : ");
                seatColumn = sc.nextInt();
                if (Utilities.checkInputs(noOfSeatSection)) {
                    throw new AppServiceException("Number of column input value should be more than 0");
                }
                if (i == 1) {
                    sectionList.add(new SeatSection(new String[seatRow][seatColumn], ConstantsUtil.SECTION_TYPE_FIRST));
                } else if (i == noOfSeatSection) {
                    sectionList.add(new SeatSection(new String[seatRow][seatColumn], ConstantsUtil.SECTION_TYPE_LAST));
                } else {
                    sectionList.add(new SeatSection(new String[seatRow][seatColumn], ConstantsUtil.SECTION_TYPE_NORMAL));
                }
            }

            System.out.print("Input total number of passengers : ");
            int totalNoOfPassengers = sc.nextInt();
            if (Utilities.checkInputs(totalNoOfPassengers)) {
                throw new AppServiceException("Number of passengers input value should be more than 0");
            }
            int passengersInQueue =
                    AppServiceProvider.getInstance().managerAllSeats(sectionList, maxRow, totalNoOfPassengers);
            AppServiceProvider.getInstance().displaySeats(sectionList, maxRow);
            System.out.println("Numbers of passengers waiting in queue : " + passengersInQueue);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Application exited with error : " + e);
        }
    }


}
