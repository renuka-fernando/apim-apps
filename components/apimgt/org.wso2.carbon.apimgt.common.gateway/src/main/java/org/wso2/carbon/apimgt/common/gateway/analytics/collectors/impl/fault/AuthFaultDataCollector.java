/*
 *  Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.wso2.carbon.apimgt.common.gateway.analytics.collectors.impl.fault;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.common.gateway.analytics.collectors.AnalyticsDataProvider;
import org.wso2.carbon.apimgt.common.gateway.analytics.exceptions.AnalyticsException;
import org.wso2.carbon.apimgt.common.gateway.analytics.publishers.RequestDataPublisher;
import org.wso2.carbon.apimgt.common.gateway.analytics.publishers.dto.Application;
import org.wso2.carbon.apimgt.common.gateway.analytics.publishers.dto.Event;
import org.wso2.carbon.apimgt.common.gateway.analytics.publishers.dto.enums.FaultCategory;
import org.wso2.carbon.apimgt.common.gateway.analytics.publishers.impl.FaultyRequestDataPublisher;

/**
 * Auth faulty request data collector.
 */
public class AuthFaultDataCollector extends AbstractFaultDataCollector {
    private static final Log log = LogFactory.getLog(AuthFaultDataCollector.class);

    public AuthFaultDataCollector(AnalyticsDataProvider provider, FaultCategory subType,
            RequestDataPublisher processor) {
        super(provider, subType, processor);
    }

    public AuthFaultDataCollector(AnalyticsDataProvider provider) {
        this(provider, FaultCategory.AUTH, new FaultyRequestDataPublisher());
    }

    @Override
    public void collectFaultData(Event faultyEvent) throws AnalyticsException {
        log.debug("handling auth failure analytics events");

        Application application = getUnknownApp();
        faultyEvent.setApplication(application);
        this.processRequest(faultyEvent);
    }
}
