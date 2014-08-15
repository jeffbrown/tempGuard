package shiroguard;

import org.codehaus.groovy.grails.commons.AbstractGrailsClass;

import java.util.Map;

/**
 * Bare bones example of a guard artefact type
 */
public class DefaultGuardClass extends AbstractGrailsClass implements GrailsGuardClass {

    public DefaultGuardClass() {
        super(GrailsGuardClass.class, GuardArtefactHandler.TYPE);
    }

    public DefaultGuardClass(Class clazz) {
        super(clazz, GuardArtefactHandler.TYPE);
    }

    public boolean hasPermission(Map<String, Object> requestParams) {
        return false;
    }
}
