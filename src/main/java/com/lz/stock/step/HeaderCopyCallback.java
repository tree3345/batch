package com.lz.stock.step;

import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Administrator on 2016/5/20.
 */
public class HeaderCopyCallback implements LineCallbackHandler, FlatFileHeaderCallback {
    private String header;

    @Override
    public void handleLine(String line) {
        Assert.notNull(line);
        this.header = line;
    }

    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(header+System.getProperty("line.separator"));
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
