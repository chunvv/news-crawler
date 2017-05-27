package com.chariot.shadow;

import com.chariot.shadow.date.DatePattern;
import com.chariot.shadow.news.News;
import com.chariot.shadow.news.NewsApplication;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.supplier.*;
import com.sun.syndication.io.FeedException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * News Bootstrap to start news crawler system
 * <p>
 * Created by Trung Vu on 2017/05/25.
 */
@RequiredArgsConstructor
public class NewsBootstrap {

    private NewsApplication application;
    @NonNull
    private File workingFile;
    @NonNull
    private Supplier supplier;
    @NonNull
    private Date from;
    @NonNull
    private Date to;

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
            throw new IllegalArgumentException(
                    "working directory doesn't exist or not a directory: " + workingDirectory);

        SupplierType supplier = SupplierType.get(Integer.valueOf(args[1]));

        Date from, to;
        try {
            from = DatePattern.generateFormatter(DatePattern.YYYY_MM_DD).parse(args[2]);
            to = DatePattern.generateFormatter(DatePattern.YYYY_MM_DD).parse(args[3]);

            if (from.compareTo(to) >= 0)
                throw new IllegalArgumentException("Invalid date range: from "
                        + from + " to " + to);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format. The format must be yyyyMMdd");
        }

        new NewsBootstrap(
                workingDirectory,
                new Supplier(
                        new SupplierID(supplier.getId()),
                        new SupplierCode(supplier.getCode()),
                        new SupplierName(supplier.getName())),
                from, to).
                run();
    }

    private void run() throws IOException, FeedException {
        List<News> newsList;
        switch (supplier.getIdAsInt()) {
            case 1:
                application = new NewsApplication(SupplierType.SKY_NEWS, generateNewsRequester(from, to));
                newsList = application.retrieve(workingFile);
                application.store(newsList);
                break;
            case 2:
                application = new NewsApplication(SupplierType.IT_NEWS, generateNewsRequester(from, to));
                newsList = application.retrieve(workingFile);
                application.store(newsList);
                break;
            default:
                break;
        }
    }

    private NewsRequester generateNewsRequester(Date from, Date to) {
        return new NewsRequester() {
            @Override
            public Date from() {
                return from;
            }

            @Override
            public Date to() {
                return to;
            }
        };
    }
}
