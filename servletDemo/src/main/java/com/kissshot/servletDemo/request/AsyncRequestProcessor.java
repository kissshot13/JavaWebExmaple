package com.kissshot.servletDemo.request;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;

public class AsyncRequestProcessor implements Runnable {

    private AsyncContext asynContext;
    private int milliseconds;

    public AsyncRequestProcessor() {

    }
    public AsyncRequestProcessor(AsyncContext context,int milliseconds){
        this.asynContext = context;
        this.milliseconds = milliseconds;
    }

    @Override
    public void run() {
        System.out.println("Async Supported" + asynContext.getRequest().isAsyncSupported());
        longProcessing(milliseconds);
        try {
            PrintWriter out = asynContext.getResponse().getWriter();
            out.write("Processing done for " + milliseconds + " milliseconds!!");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        asynContext.complete();
    }

    private void longProcessing(int secs) {
        try {
            Thread.sleep(secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
