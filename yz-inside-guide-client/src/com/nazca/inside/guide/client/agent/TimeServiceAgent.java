/*
 * ClientClockAgent.java
 * 
 * Copyright(c) 2007-2010 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2010-03-09 01:11:50
 */
package com.nazca.inside.guide.client.agent;

import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.inside.guide.client.ClientContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;
import com.nazca.inside.guide.common.rpc.TimeService;

/**
 *
 * @author fred
 */
public final class TimeServiceAgent {
    private static TimeServiceAgent agent = new TimeServiceAgent();
    public static TimeServiceAgent getInstance(){
        return agent;
    }
    
    private Calendar current = Calendar.getInstance();
    private int count = 0;
    private Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (count % 600 == 0) {
                startGetTime();
            }
            count++;
            current.add(Calendar.SECOND, 1);
        }
    });

    public void startSyncTime() {
        if(!timer.isRunning()){
            current = Calendar.getInstance();
            timer.start();
        }
    }

    public void stopSyncTime() {
        if(timer.isRunning()){
            timer.stop();
        }
    }

    public Date getServerTime(){
        return current.getTime();
    }

    public void addTimeServiceActionListener(ActionListener lis) {
        timer.addActionListener(lis);
    }

    public void removeTimeServiceActionListener(ActionListener lis) {
        timer.removeActionListener(lis);
    }

    private void startGetTime() {
        new Thread("GetIndideGuideTime-Thread") {
            @Override
            public void run() {
                try {
                    doGetTime();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    private void doGetTime() throws HttpRPCException{
        TimeService ts = HttpRPC.getService(TimeService.class, ClientContext.getInsideGuideServerRPCURL());
        long startSyncTime = System.currentTimeMillis();
        Calendar newCal = ts.getCurrentTime();
        long endSyncTime = System.currentTimeMillis();
        newCal.add(Calendar.MILLISECOND, (int)(endSyncTime - startSyncTime) / 2);
        current = newCal;
        System.out.println("yz indide guide time synced " + current.getTime() + " " + HttpRPC.getClientContext(ClientContext.getInsideGuideServerRPCURL()).getUserHttpSession());
    }

    public Calendar getServerCalendarTime() {
        return (Calendar)current.clone();
    }
}
