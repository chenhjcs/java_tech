package com.deri.demo.springbootgrpc.grpc.services;

import com.deri.demo.springbootgrpc.client.GrpcClientService;
import com.deri.grpc.bus.BusLineServiceGrpc;
import com.deri.grpc.bus.GetBusStopRequest;
import com.deri.grpc.bus.GetBusStopRespone;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.deri.demo.springbootgrpc.client.GrpcClientService;
import com.deri.grpc.bus.GetBusStopRespone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BusLineServiceTests {

    private static final Logger logger = Logger.getLogger(BusLineServiceTests.class.getName());

    @GrpcClient("local-grpc-server")
    private Channel serverChannel;

    @Test
    public void getBusStopResponeTestCase() {
        BusLineServiceGrpc.BusLineServiceBlockingStub blockingStub = BusLineServiceGrpc.newBlockingStub(serverChannel);
        GetBusStopRequest request = GetBusStopRequest.newBuilder().setLineCode("643").setDirection(0).build();
        GetBusStopRespone response;
        try {
            response = blockingStub.getBusStop(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            response = null;
        }
        Assert.assertNotNull(response);
    }

}

