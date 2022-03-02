/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.latencymonitor;
import org.opensearch.OpenSearchException;
import org.opensearch.index.IndexModule;
import org.opensearch.index.Index;
import org.opensearch.common.settings.Settings;
import org.opensearch.index.shard.IndexEventListener;
import org.opensearch.plugins.Plugin;

public class LatencyMonitorPlugin extends Plugin {
    
    // goal : 
    //      - build a new plugin and call this in the critical lifecycle of searching / indexing

    // approach :
    //      - Identify plugin extension point to leverage. onIndexModule
    //      - onIndexModule contains methods called addIndexEventListener
    //      - addIndexEventListener is a primary extension point for plugins
    //      - using the event listener, I can call beforeIndexCreated / afterIndexCreated

    // hook into plugin extension point
    @Override
    public void onIndexModule(IndexModule module) {

        // attatch index event listener to given module
        module.addIndexEventListener(new IndexEventListener() {
            
            // monitor index event creation
            @Override
            public void beforeIndexCreated(Index index, Settings indexSettings){

                // Placeholder reaction
                throw new OpenSearchException("Test : Latency Monitor Plugin is Called");


            }
        });
    }
}
