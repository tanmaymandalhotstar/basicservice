package hello;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.ApiResponse;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.proto.V1;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Yaml;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        String kuben = System.getenv("KUBERNETES_SERVICE_HOST");
        return "Greetings from Spring Boot! host: "+kuben;
    }

    @GetMapping("/add_job")
    public String addJob()
    {
        try {
            ApiClient client = ClientBuilder.cluster().build(); //create in-cluster client
            Configuration.setDefaultApiClient(client);
            BatchV1Api api = new BatchV1Api(client);
//            V1Job job=new V1Job();
//            job.setApiVersion("batch/v1");
//            V1ObjectMeta meta = new V1ObjectMeta();
//            meta.setName("pi");
//            Map<String,String> map= Collections.singletonMap("label1", "maximum-length-of-63-characters");
//            meta.setLabels(map);
//            meta.setAnnotations(Collections.singletonMap("annotation1", "some-very-long-annotation"));
//            job.setMetadata(meta);
//            V1JobSpec spec = new V1JobSpec();
//            V1PodTemplateSpec templateSpec = new V1PodTemplateSpec();
//
//            V1PodSpec podSpec = new V1PodSpec();
//            V1Container container = new V1Container();
//            container.setName("pi");
//            container.setImage("perl");
//            container.setArgs(Arrays.asList("perl","-Mbignum=bpi","-wle","print bpi(2000)"));
//            podSpec.addContainersItem(container);
//            podSpec.setRestartPolicy("Never");
//            templateSpec.setSpec(podSpec);
//            spec.setTemplate(templateSpec);
//            job.setSpec(spec);
            V1Job job = Yaml.loadAs(new File("/helloworld/src/main/resources/JobConfig/jobconfig.yaml"),V1Job.class);
            job.getMetadata().setName("pi-"+System.currentTimeMillis());
//
//            Yaml.addModelMap("batch/v1/Job", V1Job.class);
//            job = (V1Job) Yaml.load(new File("/helloworld/src/main/resources/JobConfig/jobconfig.yaml")); //load static yaml file
            ApiResponse<V1Job> response = api.createNamespacedJobWithHttpInfo("default", job, "true", null, null,null);
        }
        catch(java.io.IOException e)
        {
            return ExceptionUtils.getStackTrace(e);
        }
        catch (ApiException ex)
        {
            return ExceptionUtils.getStackTrace(ex);
        }


        return "Hi dude";
    }

}