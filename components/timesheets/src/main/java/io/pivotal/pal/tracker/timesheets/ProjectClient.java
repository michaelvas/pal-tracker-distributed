package io.pivotal.pal.tracker.timesheets;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestOperations;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectClient {

    private final RestOperations restOperations;
    private final String endpoint;

    private Map<Long, ProjectInfo> cachedProjects = new ConcurrentHashMap<>();

    public ProjectClient(RestOperations restOperations, String registrationServerEndpoint) {
        this.restOperations = restOperations;
        this.endpoint = registrationServerEndpoint;
    }

    @HystrixCommand(fallbackMethod = "getProjectFromCache")
    public ProjectInfo getProject(long projectId) {
        cachedProjects.put(projectId, restOperations.getForObject(endpoint + "/projects/" + projectId, ProjectInfo.class));
        return getProjectFromCache(projectId);
    }

    public ProjectInfo getProjectFromCache(long projectId) {
        return cachedProjects.get(projectId);
    }
}
