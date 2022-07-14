/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

package org.opensearch.latencytester.plugin;

import java.io.IOException;

import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.xcontent.XContentBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opensearch.common.Strings;
import org.opensearch.common.io.stream.StreamInput;
import java.util.Objects;

import org.opensearch.tasks.*;

public class TestReader implements Task.Status {

    private final Logger logger = LogManager.getLogger(TestReader.class);
    public static final String NAME = "TestReaderName";

    final String status;
    final int code;

    public TestReader(String status, int code) {
        this.status = status;
        this.code = code;
        logger.info("TEST READER : Arg constructor invoked");
    }

    public TestReader(StreamInput in) throws IOException {
        status = in.readString();
        code = in.readInt();
        logger.info("TEST READER : stream input constructor invoked");
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(status);
        out.writeInt(code);
        logger.info("TEST READER : writeTo override invoked");
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject();
        builder.field("status", status);
        builder.field("code", code);
        return builder.endObject();
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }

    // Implements equals and hashcode for testing
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != TestReader.class) {
            return false;
        }
        TestReader that = (TestReader) o;
        return Objects.equals(status, that.status) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code);
    }

}
