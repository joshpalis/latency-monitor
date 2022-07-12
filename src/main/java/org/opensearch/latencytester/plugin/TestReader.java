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
import org.opensearch.common.bytes.BytesReference;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.xcontent.XContentHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static org.opensearch.common.xcontent.XContentHelper.convertToMap;
import org.opensearch.tasks.*;

public class TestReader implements Task.Status {

    private final Logger logger = LogManager.getLogger(TestReader.class); 
    public static final String NAME = "TestReaderName";

    private final BytesReference status;

    public TestReader(BytesReference status) {
        this.status = requireNonNull(status, "test message");
        logger.info("TEST READER : BytesReference constructor invoked");
    }

    public TestReader(StreamInput in) throws IOException {
        status = in.readOptionalBytesReference();
        logger.info("TEST READER : stream input constructor invoked");
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeOptionalBytesReference(status);
        logger.info("TEST READER : writeTo override invoked");
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        try (InputStream stream = status.streamInput()) {
            return builder.rawValue(stream, XContentHelper.xContentType(status));
        }
    }

    // Note : this will be called by StreamOutput::writeNamedWriteable(), StreamInput::readNamedWriteable()
    // The writeable name is first sent across the wire, then the actual object
    @Override
    public String getWriteableName() {
        logger.info("TEST READER : writeable name invoked");
        return NAME;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }

    public Map<String, Object> toMap() {
        return convertToMap(status, false).v2();
    }

    // Implements equals and hashcode for testing
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != TestReader.class) {
            return false;
        }
        TestReader other = (TestReader) obj;
        return toMap().equals(other.toMap());
    }

    @Override
    public int hashCode() {
        return toMap().hashCode();
    }
    
}
