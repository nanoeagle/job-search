package com.example.jobsearch.util;

import java.io.IOException;

@FunctionalInterface
public interface Http {
	String get(String url) throws IOException ;
}