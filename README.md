# Capella OSLC Adapter #
This repository consists of the following projects:

- Capella (Eclipse) plugin providing interface for retrieving EMF data from Capella
- Capella (Eclipse) plugin that exposes the data retrieved by the previous plugin using embedded jetty server
- Maven project which is an OSLC Adapter generated using Eclipse Lyo based on the SysML v2 OSLC server and domain https://github.com/oslc-op/sysml-oslc-server 


# Running the projects #
Plugins are developed in Capella version 5.0.0, Build id: 202012021234.
To run the projects put the content of *ExportedPlugins/* into *dropins/* folder of the Capella installation.
Run the maven project in *OSLCAdapter/* folder: `mvn jetty:run-exploded`

# Plugin development #
In addition to the dependency on `javax.servelt-api` the CapellaServer also needs the `gson` library included ( see *ExportedPlugins/Development/* ) inside the *lib/* folder of the plugin project.
