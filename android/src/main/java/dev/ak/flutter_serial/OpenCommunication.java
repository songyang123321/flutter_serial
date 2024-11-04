/*
 * OpenCommunication.java
 * Created by: Mahad Asghar on 12/08/2022.
 *
 *  Copyright © 2022 BjsSoftSolution. All rights reserved.
 */


package dev.ak.flutter_serial;


import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.serialport.SerialPortFinder;
import android.serialport.port.BaseReader;
import android.serialport.port.LogInterceptorSerialPort;
import android.serialport.port.SerialApiManager;


public class OpenCommunication {
    private SerialApiManager spManager;
    private BaseReader baseReader;
    private String currentPort;
    public String logChannel = "";
    public String readChannel = "";

    List<String> entries = new ArrayList<String>();
    List<String> entryValues = new ArrayList<String>();

    public void destroyResources() {
        spManager.destroy();
    }

    public void initData() {

        spManager = SerialApiManager.getInstances().setLogInterceptor(new LogInterceptorSerialPort() {
            @Override
            public void log(@SerialApiManager.Type final String type, final String port, final boolean isAscii, final String log) {
//                Log.d("SerialPortLog", new StringBuffer()
//                        .append("Serial Port ：").append(port)
//                        .append("\ndata format ：").append(isAscii ? "ascii" : "hexString")
//                        .append("\ntype：").append(type)
//                        .append("messages：").append(log).toString());
//                logChannel += "\n" + (new StringBuffer()
//                        .append(" ").append(port)
//                        .append(" ").append(isAscii ? "ascii" : "hexString")
//                        .append(" ").append(type)
//                        .append("：").append(log)
//                        .append("\n").toString());

                Map<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("LogChannel", log);
//                dataMap.put("LogChannel",logChannel);
                dataMap.put("readChannel", readChannel);
                CustomEventHandler.sendEvent(dataMap);
            }

        });
        baseReader = new BaseReader() {
            @Override
            protected void onParse(final String port, final boolean isAscii, final String read) {
//                Log.d("SerialPortRead", new StringBuffer()
//                        .append(port).append("/").append(isAscii ? "ascii" : "hex")
//                        .append(" read：").append(read).append("\n").toString());
//                readChannel += "\n" + (new StringBuffer()
//                        .append(port).append("/").append(isAscii ? "ascii" : "hex")
//                        .append(" read：").append(read).append("\n").toString());
                Map<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("LogChannel", logChannel);
//                dataMap.put("readChannel",readChannel);
                dataMap.put("readChannel", read);
                CustomEventHandler.sendEvent(dataMap);

            }
        };
    }

    List<String> sendDeviceData() {
        SerialPortFinder mSerialPortFinder = new SerialPortFinder();
        entries = Arrays.asList(mSerialPortFinder.getAllDevicesPath());
        // entries = List.of(mSerialPortFinder.getAllDevices());
//        entryValues = List.of(mSerialPortFinder.getAllDevicesPath());
        entryValues = Arrays.asList(mSerialPortFinder.getAllDevicesPath());
        return entryValues;
    }


    public boolean open(String name, boolean isAscii, int baudRate) {
        initData();
        String checkPort = name;
        Log.d("OpenCommunication", "开始打开串口");
        if (TextUtils.isEmpty(checkPort)) {
            Log.d("OpenCommunication", "串口名称不能为空，请检查");
            return false;
        }

        if (TextUtils.equals(currentPort, checkPort)) {
            Log.d("OpenCommunication", "当前串口已打开");
            return true;
        }

        if (!TextUtils.isEmpty(currentPort)) {
            // Close the CurrentPort serial port
            Log.d("OpenCommunication", "当前已连接串口不为空，断开当前串口");
            spManager.stopSerialPort(currentPort);
        }

        if (entryValues.contains(checkPort)) {
            Log.d("OpenCommunication", "已经找到对应串口，开始连接：" + checkPort);
            currentPort = checkPort;
            spManager.startSerialPort(checkPort, isAscii, baseReader, baudRate);
            changeCode(isAscii);
            boolean isOpen = spManager.isStart(checkPort);
            Log.d("OpenCommunication", "串口名称：" + checkPort + "  连接状态：" + isOpen);
            return isOpen;
        }
        Log.d("OpenCommunication", "未找到串口，串口名称：" + checkPort);
        return false;
    }


    public void close() {
        if (!TextUtils.isEmpty(currentPort)) {
            // currentPort
            spManager.stopSerialPort(currentPort);
            currentPort = "";
        }
    }

    public void send(String sendCommand) {
        if (TextUtils.isEmpty(currentPort)) {
            return;
        }

        if (TextUtils.isEmpty(sendCommand)) {
            return;
        }
        // send data
        spManager.send(currentPort, sendCommand);
    }

    private void changeCode(boolean isAscii) {
        if (TextUtils.isEmpty(currentPort)) {
            return;
        }
        spManager.setReadCode(currentPort, isAscii);
    }


}
