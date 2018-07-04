package com.deri.demo.springbootgrpc.controller;

import com.deri.demo.springbootgrpc.client.GrpcClientService;
import com.deri.grpc.bus.CityBusLineStop;
import com.deri.grpc.bus.GetBusStopRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/client")
public class GrpcClientController {
    @Autowired
    private GrpcClientService grpcClientService;

    @RequestMapping("/getBusStopRespone")
    public Map getBusStopRespone(@RequestParam("line_code") String lineCode, @RequestParam("direction") int direction) {
        GetBusStopRespone busStopRespone = grpcClientService.getBusStopRespone(lineCode, direction);
        Map map = new HashMap();
        map.put("lineCode", busStopRespone.getLineCode());
        map.put("direction", busStopRespone.getDirection());

        List list = new ArrayList();
        Iterator iter = busStopRespone.getStopsList().iterator();
        while (iter.hasNext()) {
            Map tmp = new HashMap();
            CityBusLineStop stop = (CityBusLineStop) iter.next();
            tmp.put("stopCode", stop.getStopCode());
            tmp.put("stopName", stop.getStopName());
            tmp.put("stopOrder", stop.getStopOrder());
            tmp.put("lon", stop.getLon());
            tmp.put("lat", stop.getLat());

            list.add(tmp);
        }
        map.put("stops", list);
        return map;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello 123 abc 123 123 !!!";
    }
}
