package com.goodworkalan.okay.annotations;

import com.goodworkalan.cafe.ProjectModule;
import com.goodworkalan.cafe.builder.Builder;
import com.goodworkalan.cafe.outline.JavaProject;

/**
 * Build definition for Okay Annotations.
 *
 * @author Alan Gutierrez
 */
public class OkayAnnotationsProject implements ProjectModule {
    /**
     * Build the project definition for Okay Annotations.
     *
     * @param builder
     *          The project builder.
     */
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.okay/okay-annotations/0.1")
                .depends()
                    .production("com.github.bigeasy.okay/okay/0.+1")
                    .development("org.testng/testng-jdk15/5.10")
                    .end()
                .end()
            .end();
    }
}
