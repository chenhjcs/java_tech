package com.deri.demo.springbootgrpc.grpc.services;

import com.deri.grpc.bus.*;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

import java.util.concurrent.atomic.AtomicInteger;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

@GrpcService(BusLineServiceGrpc.class)
public class BusLineService extends BusLineServiceGrpc.BusLineServiceImplBase {

    /**
     * <pre>
     * 获取公交线路（上行或下行）的站点信息
     * </pre>
     */
    public void getBusStop(com.deri.grpc.bus.GetBusStopRequest request,
                           io.grpc.stub.StreamObserver<com.deri.grpc.bus.GetBusStopRespone> responseObserver) {
        String lineCode = request.getLineCode();
        int direction = request.getDirection();
        CityBusLineStop stop = CityBusLineStop.newBuilder().setStopCode("027-1326").setStopName("武汉火车站").setStopOrder(1)
                .setLat(30.607467836314356).setLon(114.42294878329942).build();

        GetBusStopRespone.Builder responseBuilder = GetBusStopRespone.newBuilder();
        responseBuilder.setLineCode(lineCode).setDirection(direction);
        responseBuilder.addStops(stop);

        GetBusStopRespone response = responseBuilder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    /**
     * <pre>
     * 获取公交线路实时车辆信息
     * </pre>
     */
    public void getBusPostion(com.deri.grpc.bus.GetBusPositionRequest request,
                              io.grpc.stub.StreamObserver<com.deri.grpc.bus.GetBusPositionRespone> responseObserver) {

        String lineCode = request.getLineCode();
        int direction = request.getDirection();


        GetBusPositionRespone.Builder responseBuilder = GetBusPositionRespone.newBuilder();
        responseBuilder.setLineCode(lineCode).setDirection(direction);

        BusPosition busPosition = BusPosition.newBuilder().setBusCode("62137")
                .setLat(30.607467836314356).setLon(114.42294878329942)
                .setStationNo(4).setStationStatus(1).build();
        responseBuilder.getBusesList().add(busPosition);
        busPosition = BusPosition.newBuilder().setBusCode("62118")
                .setLat(30.582876445197723).setLon(114.4271659604001)
                .setStationNo(11).setStationStatus(1).build();
        responseBuilder.getBusesList().add(busPosition);

        GetBusPositionRespone response = responseBuilder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
