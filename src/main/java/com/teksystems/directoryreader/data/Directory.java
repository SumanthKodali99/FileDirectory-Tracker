package com.teksystems.directoryreader.data;

/**
 * Specific implementation of {@link AbstractResource} which represents a directory
 */
public class Directory extends AbstractResource {

    public Directory(final String name, final String url) {
        super(name, url);
    }

	/**
	 * Specific implementation which prints a directory along with all children recursively
	 * @param prefix prefix to be used
	 */
	@Override
	public void print(final String prefix) {
		System.out.println(prefix + "- Project: " + getName() + " - URL: " + getUrl());
		getChildren().forEach(ar -> ar.print(prefix + "\t"));
	}

}
