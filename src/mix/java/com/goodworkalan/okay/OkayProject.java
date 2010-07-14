package com.goodworkalan.stringbeans.jpa.mix;

import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.cookbook.JavaProject;

/**
 * Build definition for Okay.
 *
 * @author Alan Gutierrez
 */
public class OkayProject implements ProjectModule {
    /**
     * Build the project definition for Okay.
     *
     * @param builder
     *          The project builder.
     */
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.okay/okay/0.1.0.2")
                .depends()
                    .production("com.github.bigeasy.string-beans/string-beans/0.+1")
                    .production("com.github.bigeasy.reflective/reflective-getter/0.+1")
                    .production("com.github.bigeasy.danger/danger/0.+1")
                    .production("com.github.bigeasy.verbiage/verbiage/0.+1")
                    .development("org.testng/testng-jdk15/5.10")
                    .development("org.mockito/mockito-core/1.6")
                    .end()
                .end()
            .end();
    }
}
