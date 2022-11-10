package com.cunningbird.contractfirst.openapi.contract.mock;

import com.cunningbird.contractfirst.openapi.contract.api.PetsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pet", url = "${api.address}")
public interface PetsClient extends PetsApi {}

