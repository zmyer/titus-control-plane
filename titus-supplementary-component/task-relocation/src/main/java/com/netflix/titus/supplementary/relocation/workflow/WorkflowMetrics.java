/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.titus.supplementary.relocation.workflow;

import com.netflix.spectator.api.Gauge;
import com.netflix.spectator.api.Registry;
import com.netflix.titus.common.runtime.TitusRuntime;
import com.netflix.titus.supplementary.relocation.RelocationMetrics;

class WorkflowMetrics {

    public static String EVACUATION_METRICS = RelocationMetrics.METRIC_ROOT + "evacuation.";

    private final Registry registry;

    private final Gauge stalenessStatusGauge;
    private final Gauge stalenessTimeGauge;

    WorkflowMetrics(TitusRuntime titusRuntime) {
        this.registry = titusRuntime.getRegistry();
        this.stalenessStatusGauge = registry.gauge(EVACUATION_METRICS + "stalenessStatus");
        this.stalenessTimeGauge = registry.gauge(EVACUATION_METRICS + "stalenessMs");
    }

    void setStaleness(boolean stalenessStatus, long stalenessMs) {
        stalenessStatusGauge.set(stalenessStatus ? 1 : 0);
        stalenessTimeGauge.set(stalenessMs);
    }
}
