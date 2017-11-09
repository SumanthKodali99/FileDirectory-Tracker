package com.teksystems.directoryreader.data;

/**
 * Specific implementation of {@link AbstractResource} which represents a document
 */
public class Document extends AbstractResource {

    private final String extension;

    public Document(final String name, final String url, final String extension) {
        super(name, url);
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

	/**
	 * Specific implementation which prints a document
	 * @param prefix prefix to be used
	 */
    @Override
    public void print(final String prefix) {
        System.out.println(prefix + "- Document: " + getName() + " - Extension: " + getExtension() + " - URL: " + getUrl());
    }

}
