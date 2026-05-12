package com.amazonaws.auth.policy.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.policy.Action;
import com.amazonaws.auth.policy.Condition;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Principal.WebIdentityProviders;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.Statement.Effect;
import com.amazonaws.util.json.AwsJsonReader;
import com.amazonaws.util.json.JsonUtils;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class JsonPolicyReader {
    private static final String PRINCIPAL_SCHEMA_SERVICE = "Service";
    private static final String PRINCIPAL_SCHEMA_USER = "AWS";
    private static final String PRINICIPAL_SCHEMA_FEDERATED = "Federated";
    private AwsJsonReader reader;

    private static class NamedAction implements Action {
        private final String actionName;

        public NamedAction(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return this.actionName;
        }
    }

    public Policy createPolicyFromJsonString(String jsonString) {
        if (jsonString == null) {
            throw new IllegalArgumentException("JSON string cannot be null");
        }
        this.reader = JsonUtils.getJsonReader(new StringReader(jsonString));
        Policy policy = new Policy();
        List<Statement> statements = new LinkedList();
        try {
            this.reader.beginObject();
            while (this.reader.hasNext()) {
                String name = this.reader.nextName();
                if ("Id".equals(name)) {
                    policy.setId(this.reader.nextString());
                } else if (JsonDocumentFields.STATEMENT.equals(name)) {
                    this.reader.beginArray();
                    while (this.reader.hasNext()) {
                        statements.add(statementOf(this.reader));
                    }
                    this.reader.endArray();
                } else {
                    this.reader.skipValue();
                }
            }
            this.reader.endObject();
            try {
                this.reader.close();
            } catch (IOException e) {
            }
            policy.setStatements(statements);
            return policy;
        } catch (Exception e2) {
            throw new IllegalArgumentException("Unable to generate policy object fron JSON string " + e2.getMessage(), e2);
        } catch (Throwable th) {
            try {
                this.reader.close();
            } catch (IOException e3) {
            }
        }
    }

    private Statement statementOf(AwsJsonReader reader) throws IOException {
        Statement statement = new Statement(null);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (JsonDocumentFields.STATEMENT_EFFECT.equals(name)) {
                statement.setEffect(Effect.valueOf(reader.nextString()));
            } else if (JsonDocumentFields.STATEMENT_ID.equals(name)) {
                statement.setId(reader.nextString());
            } else if (JsonDocumentFields.ACTION.equals(name)) {
                statement.setActions(actionsOf(reader));
            } else if (JsonDocumentFields.RESOURCE.equals(name)) {
                statement.setResources(resourcesOf(reader));
            } else if (JsonDocumentFields.PRINCIPAL.equals(name)) {
                statement.setPrincipals(principalOf(reader));
            } else if (JsonDocumentFields.CONDITION.equals(name)) {
                statement.setConditions(conditionsOf(reader));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return statement.getEffect() == null ? null : statement;
    }

    private List<Action> actionsOf(AwsJsonReader reader) throws IOException {
        List<Action> actions = new LinkedList();
        if (reader.isContainer()) {
            reader.beginArray();
            while (reader.hasNext()) {
                actions.add(new NamedAction(reader.nextString()));
            }
            reader.endArray();
        } else {
            actions.add(new NamedAction(reader.nextString()));
        }
        return actions;
    }

    private List<Resource> resourcesOf(AwsJsonReader reader) throws IOException {
        List<Resource> resources = new LinkedList();
        if (reader.isContainer()) {
            reader.beginArray();
            while (reader.hasNext()) {
                resources.add(new Resource(reader.nextString()));
            }
            reader.endArray();
        } else {
            resources.add(new Resource(reader.nextString()));
        }
        return resources;
    }

    private List<Principal> principalOf(AwsJsonReader reader) throws IOException {
        List<Principal> principals = new LinkedList();
        if (reader.isContainer()) {
            reader.beginObject();
            while (reader.hasNext()) {
                String schema = reader.nextName();
                if (reader.isContainer()) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        principals.add(createPrincipal(schema, reader.nextString()));
                    }
                    reader.endArray();
                } else {
                    principals.add(createPrincipal(schema, reader.nextString()));
                }
            }
            reader.endObject();
        } else {
            String s = reader.nextString();
            if ("*".equals(s)) {
                principals.add(Principal.All);
            } else {
                throw new IllegalArgumentException("Invalid principals: " + s);
            }
        }
        return principals;
    }

    private Principal createPrincipal(String schema, String principal) {
        if (schema.equalsIgnoreCase(PRINCIPAL_SCHEMA_USER)) {
            return new Principal(principal);
        }
        if (schema.equalsIgnoreCase(PRINCIPAL_SCHEMA_SERVICE)) {
            return new Principal(schema, principal);
        }
        if (!schema.equalsIgnoreCase(PRINICIPAL_SCHEMA_FEDERATED)) {
            throw new AmazonClientException("Schema " + schema + " is not a valid value for the principal.");
        } else if (WebIdentityProviders.fromString(principal) != null) {
            return new Principal(WebIdentityProviders.fromString(principal));
        } else {
            return new Principal(PRINICIPAL_SCHEMA_FEDERATED, principal);
        }
    }

    private List<Condition> conditionsOf(AwsJsonReader reader) throws IOException {
        List<Condition> conditionList = new LinkedList();
        reader.beginObject();
        while (reader.hasNext()) {
            convertConditionRecord(conditionList, reader.nextName(), reader);
        }
        reader.endObject();
        return conditionList;
    }

    private void convertConditionRecord(List<Condition> conditions, String conditionType, AwsJsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            List values = new LinkedList();
            if (reader.isContainer()) {
                reader.beginArray();
                while (reader.hasNext()) {
                    values.add(reader.nextString());
                }
                reader.endArray();
            } else {
                values.add(reader.nextString());
            }
            conditions.add(new Condition().withType(conditionType).withConditionKey(name).withValues(values));
        }
        reader.endObject();
    }
}
