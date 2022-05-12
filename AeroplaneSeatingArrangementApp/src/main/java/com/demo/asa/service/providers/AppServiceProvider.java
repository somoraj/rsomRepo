package com.demo.asa.service.providers;

import com.demo.asa.exceptions.AppServiceException;
import com.demo.asa.service.AppService;
import com.demo.asa.utils.ConstantsUtil;

import java.util.List;

public class AppServiceProvider implements AppService {

    private static class AppServiceHolder {
        public static final AppServiceProvider instance = new AppServiceProvider();
    }

    public static AppServiceProvider getInstance() {
        return AppServiceHolder.instance;
    }

    @Override
    public int managerAllSeats(List<SeatSection> sectionList, int maxRow, int noOfPassengers) throws AppServiceException {
        int passengers = 0;
        try {
            for (int i = 0; i < maxRow; i++) {
                for (SeatSection section : sectionList) {
                    if (i > section.seats.length - 1) {
                        continue;
                    }
                    passengers = fillAisleSeat(section, i, passengers, noOfPassengers);
                }
            }
            for (int i = 0; i < maxRow; i++) {
                for (SeatSection section : sectionList) {
                    if (i > section.seats.length - 1) {
                        continue;
                    }
                    passengers = fillWindowSeat(section, i, passengers, noOfPassengers);
                }
            }
            for (int i = 0; i < maxRow; i++) {
                for (SeatSection section : sectionList) {
                    if (i > section.seats.length - 1) {
                        continue;
                    }
                    passengers = fillNormalSeat(section, i, passengers, noOfPassengers);
                }
            }
        } catch(Exception e) {
            throw new AppServiceException("Error in managing all the seats : " + e);
        }
        return noOfPassengers - passengers;
    }


    private int fillAisleSeat(SeatSection section, int row, int passengers, int noOfPassengers) {
        // fill aisle
        if(section.sectionType.equals(ConstantsUtil.SECTION_TYPE_FIRST)) {
            if(section.seats[row][section.seats[row].length-1] == null) {
                if (passengers <= noOfPassengers) {
                    passengers++;
                    section.seats[row][section.seats[row].length - 1] = Integer.toString(passengers);
                } else {
                    section.seats[row][section.seats[row].length - 1] = ConstantsUtil.AISLE_SEAT;
                }
            }
        } else if (section.sectionType.equals(ConstantsUtil.SECTION_TYPE_LAST)) {
            if(section.seats[row][0] == null) {
                if (passengers <= noOfPassengers) {
                    passengers++;
                    section.seats[row][0] = Integer.toString(passengers);
                } else {
                    section.seats[row][0] = ConstantsUtil.AISLE_SEAT;
                }
            }
        } else {
            if(section.seats[row][0] == null) {
                if (passengers <= noOfPassengers) {
                    passengers++;
                    section.seats[row][0] = Integer.toString(passengers);
                } else {
                    section.seats[row][0] = ConstantsUtil.AISLE_SEAT;
                }
            }
            if (section.seats[row][section.seats[row].length-1] == null) {
                if (passengers <= noOfPassengers) {
                    passengers++;
                    section.seats[row][section.seats[row].length - 1] = Integer.toString(passengers);
                } else {
                    section.seats[row][section.seats[row].length - 1] = ConstantsUtil.AISLE_SEAT;
                }
            }
        }
        return passengers;
    }

    private int fillWindowSeat(SeatSection section, int rowNum, int passengers, int noOfPassengers) {
        // fill aisle
        if(section.sectionType.equals(ConstantsUtil.SECTION_TYPE_FIRST)) {
            if(section.seats[rowNum][0] == null) {
                if (passengers <= noOfPassengers) {
                    passengers++;
                    section.seats[rowNum][0] = Integer.toString(passengers);
                } else {
                    section.seats[rowNum][0] = ConstantsUtil.WINDOW_SEAT;
                }
            }
        } else if (section.sectionType.equals(ConstantsUtil.SECTION_TYPE_LAST)) {
            if(section.seats[rowNum][section.seats[rowNum].length - 1] == null) {
                if (passengers <= noOfPassengers) {
                    passengers++;
                    section.seats[rowNum][section.seats[rowNum].length - 1] = Integer.toString(passengers);
                } else {
                    section.seats[rowNum][section.seats[rowNum].length - 1] = ConstantsUtil.WINDOW_SEAT;
                }
            }
        }
        return passengers;
    }


    private int fillNormalSeat(SeatSection section, int rowNum, int passengers, int noOfPassengers) {

        for (int i = 1; i < section.seats[rowNum].length-1;i++) {
            if(section.seats[rowNum][i] ==  null) {
                if(passengers <= noOfPassengers) {
                    passengers++;
                    section.seats[rowNum][i] = Integer.toString(passengers);
                } else {
                    section.seats[rowNum][i] = ConstantsUtil.NORMAL_SEAT;
                }
            }
        }
        return passengers;
    }

    public void displaySeats(List<SeatSection> sectionList, int maxRow) {
        for(int i=0; i<maxRow; i++) {
            for (SeatSection section: sectionList) {
                System.out.print("    ");
                if(i > section.seats.length - 1) {
                    for (int j=0; j< section.seats[0].length; j++) {
                        System.out.print(String.format("%2s", "") + "  ");
                    }
                    continue;
                }
                for (int j=0; j< section.seats[i].length; j++) {
                    System.out.print(String.format("%2s",section.seats[i][j]) + "  ");
                }
            }
            System.out.println("");
        }
    }
}
