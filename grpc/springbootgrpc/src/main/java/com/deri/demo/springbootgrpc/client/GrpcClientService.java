package com.deri.demo.springbootgrpc.client;

import com.deri.grpc.bus.BusLineServiceGrpc;
import com.deri.grpc.bus.GetBusStopRequest;
import com.deri.grpc.bus.GetBusStopRespone;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GrpcClientService {

    private static final Logger logger = Logger.getLogger(GrpcClientService.class.getName());

    @GrpcClient("local-grpc-server")
    private Channel serverChannel;

    public GetBusStopRespone getBusStopRespone(String lineCode, int direction) {
        BusLineServiceGrpc.BusLineServiceBlockingStub blockingStub = BusLineServiceGrpc.newBlockingStub(serverChannel);
        GetBusStopRequest request = GetBusStopRequest.newBuilder().setLineCode("643").setDirection(0).build();
        GetBusStopRespone response;
        try {
            response = blockingStub.getBusStop(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            response = null;
        }

        return response;
    }
}

