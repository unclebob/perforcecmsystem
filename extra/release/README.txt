PerforceCmSystem plugin for FitNesse
-----------------------------------------------------------------------

This plugin enables Perforce revision control in FitNesse. The plugin is
intended to perform basic operations like opening pages for edit, delete, add,
reopen integrated files and revert files if neccessary. Remember that each page
is defined by a directory that bears it's name, and two files that contain it's
content. The first file is content.txt which holds the wiki text. The second is
properties.xml which holds all the metatdata for the page. This plugin will open
those files in the default changelist. The user has to take care on his own to
submit these files.

For security reasons the plugins.properties include a content filter, which will
permit to use P4PASSWD in any test page. If you're not using ticket based
authorization with perforce, you should either use the included
plugins.properties file or add the following line to your plugins.properties,
before startin FitNesse:

       ContentFilter=fitnesse.wiki.cmSystems.PerforceContentFilter

In order to use this plugin, you have to include PerforceCmSystem on the
classpath when starting FitNesse. An example how to do this is included in the
distribution. When you have started up FitNesse with the PerforceCmSystem in
your classpath, you have to set CM_SYSTEM to 
fitnesse.wiki.cmSystems.PerforceCmSystem. This can be done in one of three ways:

1) use 
       !define CM_SYSTEM {fitnesse.wiki.cmSystems.PerforceCmSystem}
   on the top-most page within FitNesse,
2) start FitNesse with -DCM_SYSTEM=fitnesse.wiki.cmSystems.PerforceCmSystem or
   some other way to set the CM_SYSTEM property within the JVM,
3) export the environment variable CM_SYSTEM with the content
   fitnesse.wiki.cmSystems.PerforceCmSystem. How to do this is shell dependent.

After having started FitNesse with the proper classpath addition and the
CM_SYSTEM variable defined to the PerforceCmSystem class, FitNesse should use
Perforce when you add a page, edit one, refactor one, having the according
content.txt and properties.xml files being opened in the default changelist.

If you don't want to add entries in a certain directory like ErrorLogs and
RecentChanges contents to your changelist, you should use the !define approach
on the top-most fitnesse suite right below the root folder. Initially I included
a mechanism to ignore these files all the time, but realized that there might be
people who would like to include these files in the version control, too. So I
dropped this feature.
