/*
 * Copyright (c) 2016 University of Ulm
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.github.cloudiator.cloudharmony;

import io.github.cloudiator.cloudharmony.api.ApiApi;
import io.github.cloudiator.cloudharmony.model.CloudService;
import io.github.cloudiator.cloudharmony.model.ComputeInstanceType;
import io.github.cloudiator.cloudharmony.model.ComputeProperties;
import io.github.cloudiator.cloudharmony.model.IDList;

import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        final ApiClient apiClient = new ApiClient();
        final ApiApi apiApi = new ApiApi(apiClient);
        try {
            final IDList providers =
                apiApi.getProviders(null, Collections.emptyList(), Collections.emptyList());
            providers.getIds().forEach(System.out::println);

            System.out.println("------------------------------------------");

            final IDList services =
                apiApi.getServices(null, Collections.emptyList(), Collections.emptyList());
            services.getIds().forEach(System.out::println);

            System.out.println("------------------------------------------");

            final CloudService ec2Service = apiApi.getService("aws:ec2", null);
            System.out.println(ec2Service);

            System.out.println("------------------------------------------");

            final List<ComputeProperties> computeProperties =
                apiApi.getComputeProperties("aws:ec2", null, null);

            computeProperties.forEach(System.out::println);

            System.out.println("------------------------------------------");

            final ComputeInstanceType computeInstanceType = apiApi
                .getComputeInstanceType("aws:ec2", "c1.xlarge", null,
                    Collections.singletonList("eu-west-1"), Collections.singletonList("linux"));

            System.out.println(computeInstanceType);
            
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
