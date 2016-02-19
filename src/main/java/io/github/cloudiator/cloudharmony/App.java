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
