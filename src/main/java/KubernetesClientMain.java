package com.skedulo.wthames;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KubernetesClientMain {

    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger("KubernetesClientMain");
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
        String masterUrl = String.format("%s:%s", System.getenv("KUBERNETES_SERVICE_HOST"), System.getenv("KUBERNETES_SERVICE_PORT"));
        System.out.println("BE#006656 masterUrl :" + masterUrl);
        Config config = ((new ConfigBuilder()).withMasterUrl(masterUrl)).build();
        KubernetesClient client = (KubernetesClient)(new DefaultKubernetesClient(config)).inNamespace(System.getenv("KUBERNETES_NAMESPACE"));
        NamespaceList myNs = client.namespaces().list();
        for (Namespace ns : myNs.getItems()) {
            System.out.println(ns.toString());
        }
    }
}