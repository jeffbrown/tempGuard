import shiroguard.DefaultGuardClass
import shiroguard.GrailsGuardClass
import shiroguard.GuardArtefactHandler

class ShiroGuardGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Shiro Guard Plugin" // Headline display name of the plugin
    def author = "Jake Ruth"
    def authorEmail = "jruth@commercehub.com"
    def description = 'This plugin creates a guard artefact Guard class.  The purpose this class is to decide if a user should have' +
            'access to an action on a controller.  Typically the name of a guard class will match a controller name in your project.'

    def artefacts = [ GuardArtefactHandler ]

    // watch for any changes in these directories
    def watchedResources = [
            "file:./grails-app/guards/**/*Guard.groovy"
    ]


    def doWithSpring = {
        println "Here is the do with spring debug"
        application.guardClasses.each { guardClass ->
            println "\nDEBUG YOO: ${guardClass.propertyName}\t\t${guardClass.clazz}"
            "${guardClass.propertyName}"(guardClass.clazz) { bean ->
                name = guardClass.name
                guardClass = guardClass.clazz
                bean.autowire = "byName"
            }
        }
    }

    def onChange = { event ->
        if (application.isArtefactOfType(GuardArtefactHandler.TYPE, event.source)) {
            println "\n***\nplguin debug: old class:  ${event.source.name}"
            def oldClass = application.getGuardClass(event.source.name)
            application.addArtefact(GuardArtefactHandler.TYPE, event.source)

            // Reload subclasses
            application.guardClasses.each {
                if (it.clazz != event.source && oldClass.clazz.isAssignableFrom(it.clazz)) {
                    def newClass = application.classLoader.reloadClass(it.clazz.name)
                    application.addArtefact(GuardArtefactHandler.TYPE, newClass)
                }
            }
        }
    }
}
