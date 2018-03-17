package com.intercom.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by hadoop on 13/3/18.
 */
@Data
@AllArgsConstructor
public class LocationEntity {
    // GENERATING unique id to identify
    double latitude;
    double longitude;
}
