/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.query;

import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

/**
 * A utils for a field based on a term.
 *
 *
 */
public class RedisFilterBuilder extends BaseFilterBuilder {

    private final String name;

    private final Object value;

    private Boolean cache;
    private String cacheKey;

    private String filterName;

    /**
     * A utils for a field based on a term.
     *
     * @param name  The field name
     * @param value The term value
     */
    public RedisFilterBuilder(String name, String value) {
        this(name, (Object) value);
    }

    /**
     * A utils for a field based on a term.
     *
     * @param name  The field name
     * @param value The term value
     */
    public RedisFilterBuilder(String name, int value) {
        this(name, (Object) value);
    }

    /**
     * A utils for a field based on a term.
     *
     * @param name  The field name
     * @param value The term value
     */
    public RedisFilterBuilder(String name, long value) {
        this(name, (Object) value);
    }

    /**
     * A utils for a field based on a term.
     *
     * @param name  The field name
     * @param value The term value
     */
    public RedisFilterBuilder(String name, float value) {
        this(name, (Object) value);
    }

    /**
     * A utils for a field based on a term.
     *
     * @param name  The field name
     * @param value The term value
     */
    public RedisFilterBuilder(String name, double value) {
        this(name, (Object) value);
    }

    /**
     * A utils for a field based on a term.
     *
     * @param name  The field name
     * @param value The term value
     */
    public RedisFilterBuilder(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Sets the utils name for the utils that can be used when searching for matched_filters per hit.
     */
    public RedisFilterBuilder filterName(String filterName) {
        this.filterName = filterName;
        return this;
    }

    /**
     * Should the utils be cached or not. Defaults to <tt>true</tt>.
     */
    public RedisFilterBuilder cache(boolean cache) {
        this.cache = cache;
        return this;
    }

    public RedisFilterBuilder cacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
        return this;
    }

    @Override
    public void doXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(RedisFilterParser.NAME);
        builder.field(name, value);
        if (filterName != null) {
            builder.field("_name", filterName);
        }
        if (cache != null) {
            builder.field("_cache", cache);
        }
        if (cacheKey != null) {
            builder.field("_cache_key", cacheKey);
        }
        builder.endObject();
    }
}