package com.example.location;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.WayPoint;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class LocationApplicationTests {

    @Test
    void contextLoads() throws IOException {


        GPX gpx;


        System.out.println(ResourceUtils.CLASSPATH_URL_PREFIX);
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/file.gpx");
        gpx = GPX.read(file.toPath());
        System.out.println(gpx.getRoutes());
        List<Track> trackList = gpx.getTracks();
        List<WayPoint> wayPointList = trackList.get(0).getSegments().get(0).getPoints();

        System.out.println(wayPointList);
        System.out.println(trackList.get(0).getSegments());
        for (WayPoint w : wayPointList) {
            System.out.println(w.getLatitude() + " " + w.getLongitude());
        }

    }

}
