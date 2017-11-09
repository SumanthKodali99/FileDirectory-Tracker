package com.teksystems.directoryreader.data;

import java.util.LinkedList;
import java.util.List;

/**
 * General representation of directory or a document
 */
public abstract class AbstractResource {

    private final String name;
    private final String url;
    private final List<AbstractResource> children = new LinkedList<>();

    public AbstractResource(final String name, final String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<AbstractResource> getChildren() {
        return children;
    }

	/**
	 * Method responsible for printing a resource
	 * @param prefix prefix to be used
	 */
	public abstract void print(final String prefix);

}
