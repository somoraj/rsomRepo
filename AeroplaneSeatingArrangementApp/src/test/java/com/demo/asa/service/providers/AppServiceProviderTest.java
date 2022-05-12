package com.demo.asa.service.providers;

import com.demo.asa.exceptions.AppServiceException;
import com.demo.asa.utils.ConstantsUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppServiceProviderTest {

    @Test
    public void managerAllSeats() throws AppServiceException {
        List<SeatSection> sectionList = new ArrayList<>();
        int maxRow = 4;
        int noOfPassengers = 100;

        sectionList.add(new SeatSection(new String[3][4], ConstantsUtil.SECTION_TYPE_FIRST));
        sectionList.add(new SeatSection(new String[4][5], ConstantsUtil.SECTION_TYPE_NORMAL));
        sectionList.add(new SeatSection(new String[2][3], ConstantsUtil.SECTION_TYPE_NORMAL));
        sectionList.add(new SeatSection(new String [3][4], ConstantsUtil.SECTION_TYPE_LAST));

        int passengersInQueue = AppServiceProvider.getInstance().managerAllSeats(sectionList, maxRow, noOfPassengers);
        Assert.assertEquals(passengersInQueue, 50);
    }
}
