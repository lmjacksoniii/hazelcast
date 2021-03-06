/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.scheduledexecutor;

import com.hazelcast.client.test.TestHazelcastFactory;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.scheduledexecutor.IScheduledExecutorService;
import com.hazelcast.scheduledexecutor.ScheduledExecutorServiceBasicTest;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.After;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelTest.class})
public class ClientScheduledExecutorServiceBasicTest extends ScheduledExecutorServiceBasicTest {

    private TestHazelcastFactory factory;

    @After
    public void teardown() {
        if (factory != null) {
            factory.terminateAll();
        }
    }

    @Override
    public HazelcastInstance[] createClusterWithCount(int count) {
        factory = new TestHazelcastFactory();
        return factory.newInstances(new Config(), count);
    }

    @Override
    public IScheduledExecutorService getScheduledExecutor(HazelcastInstance[] instances, String name) {
        return factory.newHazelcastClient().getScheduledExecutorService(name);
    }
}
