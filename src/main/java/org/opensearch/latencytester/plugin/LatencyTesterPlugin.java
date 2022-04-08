/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.latencytester.plugin;

import org.opensearch.index.IndexModule;
import org.opensearch.index.IndexService;
import org.opensearch.index.engine.Engine;
import org.opensearch.index.Index;
import org.opensearch.common.settings.Settings;
import org.opensearch.index.shard.IndexEventListener;
import org.opensearch.index.shard.IndexingOperationListener;
import org.opensearch.index.shard.SearchOperationListener;
import org.opensearch.indices.cluster.IndicesClusterStateService.AllocatedIndices.IndexRemovalReason;
import org.opensearch.plugins.Plugin;
import org.opensearch.search.internal.SearchContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.opensearch.index.shard.ShardId;

public class LatencyTesterPlugin extends Plugin {

    // goal :
    // - build a new plugin and call this in the critical lifecycle of searching / indexing

    // approach :
    // - Identify plugin extension point to leverage. onIndexModule
    // - onIndexModule contains methods called addIndexEventListener
    // - addIndexEventListener is a primary extension point for plugins
    // - using the event listener, I can call beforeIndexCreated / afterIndexCreated

    private static final Logger logger = LogManager.getLogger(LatencyTesterPlugin.class);

    // hook into plugin extension point for index events
    @Override
    public void onIndexModule(IndexModule module) {

        module.addIndexEventListener(new IndexEventListener() {

            @Override
            public void beforeIndexRemoved(IndexService indexService, IndexRemovalReason reason) {

                logger.info("TEST INDEX EVENT : BEFORE INDEX REMOVED");
            }
        });
    }
}
