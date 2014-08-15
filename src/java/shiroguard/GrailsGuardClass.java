package shiroguard;

import org.codehaus.groovy.grails.commons.GrailsClass;

import java.util.Map;

/**
 * Skeleton class for the new guard artefact
 */
public interface GrailsGuardClass extends GrailsClass {

    public boolean hasPermission(Map<String, Object> requestParams);

}
