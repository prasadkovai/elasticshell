/*
 * Licensed to Luca Cavanna (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
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
package org.elasticsearch.shell.client.builders.indices;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequest;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.shell.JsonSerializer;
import org.elasticsearch.shell.client.builders.AbstractRequestBuilder;

import java.io.IOException;

/**
 * @author Luca Cavanna
 *
 * Request builder for (put) index template API
 */
@SuppressWarnings("unused")
public class PutIndexTemplateRequestBuilder<JsonInput, JsonOutput> extends AbstractRequestBuilder<PutIndexTemplateRequest, PutIndexTemplateResponse, JsonInput, JsonOutput> {

    public PutIndexTemplateRequestBuilder(Client client, JsonSerializer<JsonInput, JsonOutput> jsonSerializer) {
        super(client, new PutIndexTemplateRequest(null), jsonSerializer);
    }

    public PutIndexTemplateRequestBuilder template(String template) {
        request.template(template);
        return this;
    }

    public PutIndexTemplateRequestBuilder order(int order) {
        request.order(order);
        return this;
    }

    public PutIndexTemplateRequestBuilder create(boolean create) {
        request.create(create);
        return this;
    }

    public PutIndexTemplateRequestBuilder settings(String source) {
        request.settings(source);
        return this;
    }

    public PutIndexTemplateRequestBuilder settings(JsonInput source) {
        request.settings(jsonToString(source));
        return this;
    }

    public PutIndexTemplateRequestBuilder mapping(String type, String source) {
        request.mapping(type, source);
        return this;
    }

    public PutIndexTemplateRequestBuilder mapping(String type, JsonInput source) {
        request.mapping(type, jsonToString(source));
        return this;
    }

    public PutIndexTemplateRequestBuilder cause(String cause) {
        request.cause(cause);
        return this;
    }

    public PutIndexTemplateRequestBuilder source(String templateSource) {
        request.source(templateSource);
        return this;
    }

    public PutIndexTemplateRequestBuilder source(JsonInput templateSource) {
        request.source(jsonToString(templateSource));
        return this;
    }

    public PutIndexTemplateRequestBuilder timeout(String timeout) {
        request.timeout(timeout);
        return this;
    }

    @Override
    protected ActionFuture<PutIndexTemplateResponse> doExecute(PutIndexTemplateRequest request) {
        return client.admin().indices().putTemplate(request);
    }

    @Override
    protected XContentBuilder toXContent(PutIndexTemplateRequest request, PutIndexTemplateResponse response, XContentBuilder builder) throws IOException {
        builder.startObject()
                .field(Fields.OK, true)
                .field(Fields.ACKNOWLEDGED, response.acknowledged())
                .endObject();
        return builder;
    }
}