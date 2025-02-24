/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.data.pipeline.core.task;

import org.apache.shardingsphere.data.pipeline.api.config.rulealtered.TaskConfiguration;
import org.apache.shardingsphere.data.pipeline.api.ingest.position.PlaceholderPosition;
import org.apache.shardingsphere.data.pipeline.core.util.ResourceUtil;
import org.apache.shardingsphere.data.pipeline.core.util.RuleAlteredContextUtil;
import org.apache.shardingsphere.data.pipeline.scenario.rulealtered.RuleAlteredJobContext;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public final class IncrementalTaskTest {
    
    private IncrementalTask incrementalTask;
    
    @BeforeClass
    public static void beforeClass() {
        RuleAlteredContextUtil.mockModeConfig();
    }
    
    @Before
    public void setUp() {
        TaskConfiguration taskConfig = new RuleAlteredJobContext(ResourceUtil.mockJobConfig()).getTaskConfigs().iterator().next();
        taskConfig.getDumperConfig().setPosition(new PlaceholderPosition());
        incrementalTask = new IncrementalTask(3, taskConfig.getDumperConfig(), taskConfig.getImporterConfig(), RuleAlteredContextUtil.getExecuteEngine());
    }
    
    @Test
    public void assertStart() {
        incrementalTask.start();
        assertThat(incrementalTask.getTaskId(), is("ds_0"));
        assertTrue(incrementalTask.getProgress().getPosition() instanceof PlaceholderPosition);
    }
    
    @After
    public void tearDown() {
        incrementalTask.stop();
    }
}
