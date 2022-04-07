package org.opensearch.latencytester.transportservice;

public class ExtensionSettings {

    private String extensionname;
    private String hostaddress;
    private String hostport;
    private String description;
    private String version;
    private String opensearchversion;

    // Change the location to extension.yml file of the extension
    public static final String EXTENSION_DESCRIPTOR = "src/test/resources/extension.yml";

    public String getExtensionname() {
        return extensionname;
    }

    public void setExtensionname(String extensionname) {
        this.extensionname = extensionname;
    }

    public String getHostaddress() {
        return hostaddress;
    }

    public void getHostaddress(String hostaddress) {
        this.hostaddress = hostaddress;
    }

    public String getHostport() {
        return hostport;
    }

    public void setHostport(String hostport) {
        this.hostport = hostport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOpensearchversion() {
        return opensearchversion;
    }

    public void setOpensearchversion(String opensearchversion) {
        this.opensearchversion = opensearchversion;
    }
}