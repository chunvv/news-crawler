package com.chariot.shadow;

import com.chariot.shadow.date.DatePattern;
import com.chariot.shadow.supplier.SupplierType;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

/**
 * News Bootstrap to start news crawler system
 * <p>
 * Created by Trung Vu on 2017/05/25.
 */
public class NewsBootstrap {

    public static void main(String[] args) {
        if (args.length < 4)
            throw new IllegalArgumentException("Not enough arguments. Required: working directory, supplier ID, from, to");

        File workingDirecory = new File(args[0]).getAbsoluteFile();
        if (!workingDirecory.exists() || !workingDirecory.isDirectory())
            throw new IllegalArgumentException(
                    "working directory doesn't exist or not a directory: " + workingDirecory);

        SupplierType supplierId = SupplierType.get(Integer.valueOf(args[1]));

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
    }
}
