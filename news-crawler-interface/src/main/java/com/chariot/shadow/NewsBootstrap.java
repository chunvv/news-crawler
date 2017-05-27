package com.chariot.shadow;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.chariot.shadow.date.DatePattern;
import com.chariot.shadow.news.NewsApplication;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.supplier.Supplier;
import com.chariot.shadow.supplier.SupplierCode;
import com.chariot.shadow.supplier.SupplierID;
import com.chariot.shadow.supplier.SupplierName;
import com.chariot.shadow.supplier.SupplierType;
import com.sun.syndication.io.FeedException;

import lombok.Value;

/**
 * News Bootstrap to start news crawler system
 * <p>
 * Created by Trung Vu on 2017/05/25.
 */
@Value
public class NewsBootstrap {

    NewsApplication application;
    File workingFile;
    Supplier supplier;
    Date from;
    Date to;

    private void run() throws IOException, FeedException {
        application.store(application.retrieve(workingFile));
    }

    @Value
    static class BootstrapNewsRequester implements NewsRequester {

        Date from;
        Date to;

        @Override
        public Date from() {
            return getFrom();
        }

        @Override
        public Date to() {
            return getTo();
        }

    }

    /**
     * 1. Folder
     * 2: supplier id
     * 3: from date
     * 4: to date
     * /data/news 1 20170524 20170528
     *
     * @param args
     * @throws IOException
     * @throws FeedException
     */
    public static void main(String[] args) throws IOException, FeedException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Not enough arguments. Required: working directory, supplier ID, from, to");
        }

        File workingDirectory = new File(args[0]).getAbsoluteFile();
        if (!workingDirectory.exists() || !workingDirectory.isDirectory())
            throw new IllegalArgumentException("working directory doesn't exist or not a directory: " + workingDirectory);

        Date from, to;
        try {
            from = DatePattern.generateFormatter(DatePattern.YYYY_MM_DD).parse(args[2]);
            to = DatePattern.generateFormatter(DatePattern.YYYY_MM_DD).parse(args[3]);

            if (from.compareTo(to) >= 0)
                throw new IllegalArgumentException("Invalid date range: from " + from + " to " + to);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. The format must be yyyyMMdd");
        }

        SupplierType supplierType = SupplierType.get(Integer.valueOf(args[1]));
        
        new NewsBootstrap(
            new NewsApplication(supplierType, new BootstrapNewsRequester(from, to)),
            workingDirectory,
            new Supplier(
                new SupplierID(supplierType.getId()),
                new SupplierCode(supplierType.getCode()),
                new SupplierName(supplierType.getName())),
            from,
            to).run();
    }
}
