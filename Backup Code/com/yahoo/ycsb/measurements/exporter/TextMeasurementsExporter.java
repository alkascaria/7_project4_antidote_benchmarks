/**
 * Copyright (c) 2010 Yahoo! Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */
package com.yahoo.ycsb.measurements.exporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Write human readable text. Tries to emulate the previous print report method.
 */
public class TextMeasurementsExporter implements MeasurementsExporter
{

    private static final Logger log = LogManager.getLogger(TextMeasurementsExporter.class);

    private BufferedWriter bw;

    public TextMeasurementsExporter(OutputStream os)
    {
        this.bw = new BufferedWriter(new OutputStreamWriter(os));
    }

    public void write(String metric, String measurement, int i) throws IOException
    {
        String text = "[" + metric + "], " + measurement + ", " + i;
        log.trace(text);
        bw.write(text);
        bw.newLine();
    }

    public void write(String metric, String measurement, double d) throws IOException
    {
        String text = "[" + metric + "], " + measurement + ", " + d;
        log.trace(text);
        bw.write(text);
        bw.newLine();
    }

    public void close() throws IOException
    {
        this.bw.close();
    }

}
