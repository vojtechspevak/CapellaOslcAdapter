package capellaserver.domain;

import java.net.URI;

public class Link {
	private URI value;
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public URI getValue()
	{
		return value;
	}

	public void setValue(URI value)
	{
		this.value = value;
	}

    public Link() {
    }

    public Link(URI resource) {
        setValue(resource);
    }

    public Link(URI resource, String label) {
        setValue(resource);
        this.label = label;
    }

}
