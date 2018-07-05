package com.deri.demo.springbootgrpc.client;

import com.deri.grpc.bus.GetBusStopRespone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcClientServiceTests {

    @Autowired
    private GrpcClientService grpcClientService;

    @Test
    public void testGetBusStopRespone() {
        String lineCode = "392";
        int direction = 0;
        GetBusStopRespone busStopRespone = grpcClientService.getBusStopRespone(lineCode, direction);
        Assert.assertNotNull(busStopRespone);
    }

}
