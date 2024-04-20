package com.example.location;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.WayPoint;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {


    private final List<List<LatitudeLongitude>> shuttleList;

    private final int FILE_COUNT = 4;


    public Controller() throws IOException {

        shuttleList = new ArrayList<>();

        for (int i = 0; i < FILE_COUNT; i++) {
            List<LatitudeLongitude> latitudeLongitudeList = new ArrayList<>();


            GPX gpx;
            Resource resource = new ClassPathResource("static/file" + i + ".gpx");
            InputStream inputStream = resource.getInputStream();
            gpx = GPX.Reader.of(GPX.Reader.Mode.LENIENT).read(inputStream);

            List<Track> trackList = gpx.getTracks();
            List<WayPoint> wayPointList = trackList.get(0).getSegments().get(0).getPoints();
            for (WayPoint w : wayPointList) {
                latitudeLongitudeList.add(new LatitudeLongitude(w.getLatitude().doubleValue(), w.getLongitude().doubleValue()));
            }
            LatitudeLongitude.SEQUENCE = 0;
            shuttleList.add(latitudeLongitudeList);
        }
    }

    @GetMapping("/shuttle/{number}/location")
    public ResponseEntity<LatitudeLongitude> getLatitudeLongitudeByServerTime(@PathVariable int number) {
        if (number <= 0 || number > FILE_COUNT) {
            return ResponseEntity.notFound().build();
        }
        List<LatitudeLongitude> latitudeLongitudeList = shuttleList.get(number - 1);

        int idx = (int) ((System.currentTimeMillis() / 1000) % latitudeLongitudeList.size());
        return ResponseEntity.ok(latitudeLongitudeList.get(idx));
    }

    @GetMapping("/shuttle/{number}/route")
    public ResponseEntity<List<LatitudeLongitude>> getRoute(@PathVariable int number) {
        if (number <= 0 || number > FILE_COUNT) {
            return ResponseEntity.notFound().build();
        }

        List<LatitudeLongitude> latitudeLongitudeList = shuttleList.get(number - 1);
        return ResponseEntity.ok(latitudeLongitudeList);
    }


}
