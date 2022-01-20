package org.dz1.services;

import org.dz1.models.District;

public class DistrictService extends MasterService {
    public void saveDistrict(District district) {
        saveOrUpdate(district, false);
    }
}