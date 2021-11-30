
# Capella OSLC Adapter #

This repository consists of the following projects:

- Capella (Eclipse) plugin providing interface for retrieving EMF data from Capella
- Capella (Eclipse) plugin that exposes the data retrieved by the previous plugin using embedded jetty server
- Maven project which is an OSLC Adapter generated using Eclipse Lyo based on the SysML v2 OSLC server and domain https://github.com/oslc-op/sysml-oslc-server

The elements are provided using the domain of the referenced SysML v2 OSLC server project. The mapping from Capella model is however totally experimental and most Capella resources are represented as Element with only some of the properties mapped. Some of the Capella resources are mapped to SysML elements Package, Class, Connector, Relationship, Generalization and Port usage, but the mapping is present only to suggest the mapping mechanism inside of adapter and can't be itself seen as something useful.

# Running the projects #

Plugins are developed in Capella version 5.0.0, Build id: 202012021234.\

To run the projects put the content of *ExportedPlugins/* into *dropins/* folder of the Capella installation.

Once the plugins are installed the **Embedded Server** button should appear in the upper bar.\

Run the maven project in *OSLCAdapter/* folder: `mvn jetty:run-exploded`\

Homepage is available at http://localhost:1331/sysml_oslc_server/

  ## Oslc.where ##

Simple `oslc.where` query is enabled on all implemented query capabilities. Queries containing compound terms are not supported.\
Adapter supports the following properties:

- dcterms:identifier
- sysml:name (http://omg.org/ns/sysml)
- sysml:owner

Sample queries tested on the *LevelCrossingTrafficControl.zip* project that can be downloaded from https://download.eclipse.org/capella/samples/5.0.0/ \
**1.) Simple string eq query** (on QueryCapability for Class)**:** \
`?oslc.where=sysml:name="Station information system"`\
full url encoded query:\
`http://localhost:1331/sysml_oslc_server/services/projects/TGV2ZWwgQ3Jvc3NpbmcgVHJhZmZpYyBDb250cm9s/service3/sysmlClasss/query?oslc.where=sysml:name=%22Station%20information%20system%22`\
**Simple in term query** (on QueryCapability for Connector)**:** \
`?oslc.where=sysml:name in ["Road detections","Railway detections","Current situation"]`\
full url encoded query:\
`http://localhost:1331/sysml_oslc_server/services/projects/TGV2ZWwgQ3Jvc3NpbmcgVHJhZmZpYyBDb250cm9s/service13/connectors/query?oslc.where=sysml:name%20in%20[%22Road%20detections%22,%22Railway%20detections%22,%22Current%20situation%22]`\
**Compound query containing eq, neq and in term** (on QueryCapability for Element)**:** \
`?oslc.where=sysml:owner=<http://localhost:1331/sysml_oslc_server/services/projects/TGV2ZWwgQ3Jvc3NpbmcgVHJhZmZpYyBDb250cm9s/elements/6edfd3bb-6a92-46dc-b662-2100ab2674b1> and sysml:name!="Temperature Min" and dcterms:identifier in ["bbc953c7-5e0b-4454-a6a9-d949f1d271b5","7e34b60f-f1c6-4007-b015-85789c42b443","3cf289a1-d749-4afb-b52f-76753a22f8fc"]`\
full url encoded query:\
`http://localhost:1331/sysml_oslc_server/services/projects/TGV2ZWwgQ3Jvc3NpbmcgVHJhZmZpYyBDb250cm9s/service2/elements/query?oslc.where=sysml:owner=%3Chttp://localhost:1331/sysml_oslc_server/services/projects/TGV2ZWwgQ3Jvc3NpbmcgVHJhZmZpYyBDb250cm9s/elements/6edfd3bb-6a92-46dc-b662-2100ab2674b1%3E%20and%20sysml:name!=%22Temperature%20Min%22%20and%20dcterms:identifier%20in%20[%22bbc953c7-5e0b-4454-a6a9-d949f1d271b5%22,%227e34b60f-f1c6-4007-b015-85789c42b443%22,%223cf289a1-d749-4afb-b52f-76753a22f8fc%22]`

  
  
  

## Selection dialogs ##

Adapter implements very simple selections dialog. Elements description, name and identifier are searched and returned if any of them contains the provided search text.

# Plugin development #

In addition to the dependency on `javax.servelt-api` the CapellaServer also needs the `gson` library included ( see *ExportedPlugins/Development/* ) inside the *lib/* folder of the plugin project.

  



