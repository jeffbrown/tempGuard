package shiroguard;


import org.codehaus.groovy.grails.commons.ArtefactHandlerAdapter;

/**
 * This class handles the creation of guard artefacts
 */
public class GuardArtefactHandler extends ArtefactHandlerAdapter {

    public static final String TYPE = "Guard";

    public GuardArtefactHandler() {
        super(TYPE, GrailsGuardClass.class, DefaultGuardClass.class, null);
    }

    public boolean isArtefactClass(Class clazz) {
        return clazz != null && clazz.getName().endsWith(TYPE);
    }
}
