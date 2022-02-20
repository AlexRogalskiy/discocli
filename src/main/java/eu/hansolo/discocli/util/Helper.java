/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2022 Gerrit Grunwald.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.discocli.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class Helper {
    private Helper() {}

    private static HttpClient httpClient;
    private static HttpClient httpClientAsync;


    public static Distribution getDistributionFromText(final String text) {
        if (null == text) { return null; }
        switch (text) {
            case "zulu":
            case "ZULU":
            case "Zulu":
            case "zulucore":
            case "ZULUCORE":
            case "ZuluCore":
            case "zulu_core":
            case "ZULU_CORE":
            case "Zulu_Core":
            case "zulu core":
            case "ZULU CORE":
            case "Zulu Core":
                return Distro.ZULU.get();
            case "zing":
            case "ZING":
            case "Zing":
            case "prime":
            case "PRIME":
            case "Prime":
            case "zuluprime":
            case "ZULUPRIME":
            case "ZuluPrime":
            case "zulu_prime":
            case "ZULU_PRIME":
            case "Zulu_Prime":
            case "zulu prime":
            case "ZULU PRIME":
            case "Zulu Prime":
                return Distro.ZULU_PRIME.get();
            case "aoj":
            case "AOJ":
                return Distro.AOJ.get();
            case "aoj_openj9":
            case "AOJ_OpenJ9":
            case "AOJ_OPENJ9":
            case "AOJ OpenJ9":
            case "AOJ OPENJ9":
            case "aoj openj9":
                return Distro.AOJ_OPENJ9.get();
            case "corretto":
            case "CORRETTO":
            case "Corretto":
                return Distro.CORRETTO.get();
            case "dragonwell":
            case "DRAGONWELL":
            case "Dragonwell":
                return Distro.DRAGONWELL.get();
            case "graalvm_ce8":
            case "graalvmce8":
            case "GraalVM CE 8":
            case "GraalVMCE8":
            case "GraalVM_CE8":
                return Distro.GRAALVM_CE8.get();
            case "graalvm_ce11":
            case "graalvmce11":
            case "GraalVM CE 11":
            case "GraalVMCE11":
            case "GraalVM_CE11":
                return Distro.GRAALVM_CE11.get();
            case "graalvm_ce16":
            case "graalvmce16":
            case "GraalVM CE 16":
            case "GraalVMCE16":
            case "GraalVM_CE16":
                return Distro.GRAALVM_CE16.get();
            case "graalvm_ce17":
            case "graalvmce17":
            case "GraalVM CE 17":
            case "GraalVMCE17":
            case "GraalVM_CE17":
                return Distro.GRAALVM_CE17.get();
            case "jetbrains":
            case "JetBrains":
            case "JETBRAINS":
                return Distro.JETBRAINS.get();
            case "liberica":
            case "LIBERICA":
            case "Liberica":
                return Distro.LIBERICA.get();
            case "liberica_native":
            case "LIBERICA_NATIVE":
            case "libericaNative":
            case "LibericaNative":
            case "liberica native":
            case "LIBERICA NATIVE":
            case "Liberica Native":
                return Distro.LIBERICA_NATIVE.get();
            case "mandrel":
            case "MANDREL":
            case "Mandrel":
                return Distro.MANDREL.get();
            case "microsoft":
            case "Microsoft":
            case "MICROSOFT":
            case "Microsoft OpenJDK":
            case "Microsoft Build of OpenJDK":
                return Distro.MICROSOFT.get();
            case "ojdk_build":
            case "OJDK_BUILD":
            case "OJDK Build":
            case "ojdk build":
            case "ojdkbuild":
            case "OJDKBuild":
                return Distro.OJDK_BUILD.get();
            case "openlogic":
            case "OPENLOGIC":
            case "OpenLogic":
            case "open_logic":
            case "OPEN_LOGIC":
            case "Open Logic":
            case "OPEN LOGIC":
            case "open logic":
                return Distro.OPEN_LOGIC.get();
            case "oracle":
            case "Oracle":
            case "ORACLE":
                return Distro.ORACLE.get();
            case "oracle_open_jdk":
            case "ORACLE_OPEN_JDK":
            case "oracle_openjdk":
            case "ORACLE_OPENJDK":
            case "Oracle_OpenJDK":
            case "Oracle OpenJDK":
            case "oracle openjdk":
            case "ORACLE OPENJDK":
            case "open_jdk":
            case "openjdk":
            case "OpenJDK":
            case "Open JDK":
            case "OPEN_JDK":
            case "open-jdk":
            case "OPEN-JDK":
            case "Oracle-OpenJDK":
            case "oracle-openjdk":
            case "ORACLE-OPENJDK":
            case "oracle-open-jdk":
            case "ORACLE-OPEN-JDK":
                return Distro.ORACLE_OPEN_JDK.get();
            case "sap_machine":
            case "sapmachine":
            case "SAPMACHINE":
            case "SAP_MACHINE":
            case "SAPMachine":
            case "SAP Machine":
            case "sap-machine":
            case "SAP-Machine":
            case "SAP-MACHINE":
                return Distro.SAP_MACHINE.get();
            case "semeru":
            case "Semeru":
            case "SEMERU":
                return Distro.SEMERU.get();
            case "semeru_certified":
            case "SEMERU_CERTIFIED":
            case "Semeru_Certified":
            case "Semeru_certified":
            case "semeru certified":
            case "SEMERU CERTIFIED":
            case "Semeru Certified":
            case "Semeru certified":
                return Distro.SEMERU_CERTIFIED.get();
            case "temurin":
            case "Temurin":
            case "TEMURIN":
                return Distro.TEMURIN.get();
            case "trava":
            case "TRAVA":
            case "Trava":
            case "trava_openjdk":
            case "TRAVA_OPENJDK":
            case "trava openjdk":
            case "TRAVA OPENJDK":
                return Distro.TRAVA.get();
            case "kona":
            case "KONA":
            case "Kona":
                return Distro.KONA.get();
            case "bisheng":
            case "BISHENG":
            case "BiSheng":
            case "bi_sheng":
            case "BI_SHENG":
            case "bi-sheng":
            case "BI-SHENG":
            case "bi sheng":
            case "Bi Sheng":
            case "BI SHENG":
                return Distro.BISHENG.get();
            default:
                return null;
        }
    }


    // ******************** REST calls ****************************************
    private static HttpClient createHttpClient() {
        return HttpClient.newBuilder()
                         .connectTimeout(Duration.ofSeconds(20))
                         .version(Version.HTTP_2)
                         .followRedirects(Redirect.NORMAL)
                         //.executor(Executors.newFixedThreadPool(4))
                         .build();
    }

    public static final HttpResponse<String> get(final String uri) {
        return get(uri, new HashMap<>());
    }
    public static final HttpResponse<String> get(final String uri, final Map<String,String> headers) {
        if (null == httpClient) { httpClient = createHttpClient(); }

        List<String> requestHeaders = new LinkedList<>();
        requestHeaders.add("User-Agent");
        requestHeaders.add("DiscoAPI");
        headers.entrySet().forEach(entry -> {
            final String name  = entry.getKey();
            final String value = entry.getValue();
            if (null != name && !name.isEmpty() && null != value && !value.isEmpty()) {
                requestHeaders.add(name);
                requestHeaders.add(value);
            }
        });

        final HttpRequest request = HttpRequest.newBuilder()
                                               .GET()
                                               .uri(URI.create(uri))
                                               .headers(requestHeaders.toArray(new String[0]))
                                               .timeout(Duration.ofSeconds(10))
                                               .build();

        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response;
            } else {
                // Problem with url request
                //LOGGER.debug("Error executing get request {}", uri);
                //LOGGER.debug("Response (Status Code {}) {} ", response.statusCode(), response.body());
                return response;
            }
        } catch (CompletionException | InterruptedException | IOException e) {
            //LOGGER.error("Error executing get request {} : {}", uri, e.getMessage());
            return null;
        }
    }

    public static final CompletableFuture<HttpResponse<String>> getAsync(final String uri) {
        return getAsync(uri, new HashMap<>());
    }
    public static final CompletableFuture<HttpResponse<String>> getAsync(final String uri, final Map<String, String> headers) {
        if (null == httpClientAsync) { httpClientAsync = createHttpClient(); }

        List<String> requestHeaders = new LinkedList<>();
        requestHeaders.add("User-Agent");
        requestHeaders.add("DiscoAPI");
        headers.entrySet().forEach(entry -> {
            final String name  = entry.getKey();
            final String value = entry.getValue();
            if (null != name && !name.isEmpty() && null != value && !value.isEmpty()) {
                requestHeaders.add(name);
                requestHeaders.add(value);
            }
        });

        final HttpRequest request = HttpRequest.newBuilder()
                                               .GET()
                                               .uri(URI.create(uri))
                                               .headers(requestHeaders.toArray(new String[0]))
                                               .timeout(Duration.ofSeconds(10))
                                               .build();

        return httpClientAsync.sendAsync(request, BodyHandlers.ofString());
    }

    public static final HttpResponse<String> httpHeadRequestSync(final String uri) {
        if (null == httpClient) { httpClient = createHttpClient(); }

        final HttpRequest request = HttpRequest.newBuilder()
                                               .method("HEAD", HttpRequest.BodyPublishers.noBody())
                                               .uri(URI.create(uri))
                                               .build();

        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response;
            } else {
                // Problem with url request
                //LOGGER.debug("Error executing get request {}", uri);
                //LOGGER.debug("Response (Status Code {}) {} ", response.statusCode(), response.body());
                return response;
            }
        } catch (CompletionException | InterruptedException | IOException e) {
            //LOGGER.error("Error executing get request {} : {}", uri, e.getMessage());
            return null;
        }
    }
    public static final  CompletableFuture<HttpResponse<String>> httpHeadRequestAsync(final String uri) {
        if (null == httpClientAsync) { httpClientAsync = createHttpClient(); }

        final HttpRequest request = HttpRequest.newBuilder()
                                               .method("HEAD", HttpRequest.BodyPublishers.noBody())
                                               .uri(URI.create(uri))
                                               .build();
        return httpClientAsync.sendAsync(request, BodyHandlers.ofString());
    }
}
