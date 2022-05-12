package com.demo.asa.service;

import com.demo.asa.exceptions.AppServiceException;
import com.demo.asa.service.providers.SeatSection;

import java.util.List;

public interface AppService {

    int managerAllSeats(List<SeatSection> sectionList, int maxRow, int noOfPassengers) throws AppServiceException;
}
