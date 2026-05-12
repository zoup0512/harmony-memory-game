package com.amazonaws.services.s3.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BucketNotificationConfiguration {
    private List<TopicConfiguration> topicConfigurations;

    public static class TopicConfiguration {
        private final String event;
        private final String topic;

        public TopicConfiguration(String topic, String event) {
            this.topic = topic;
            this.event = event;
        }

        public String getTopic() {
            return this.topic;
        }

        public String getEvent() {
            return this.event;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("{");
            sb.append("Topic: " + getTopic() + ", ");
            sb.append("Event: " + getEvent() + ", ");
            sb.append("}");
            return sb.toString();
        }
    }

    public BucketNotificationConfiguration() {
        this.topicConfigurations = null;
        this.topicConfigurations = new ArrayList(1);
    }

    public BucketNotificationConfiguration(Collection<TopicConfiguration> topicConfigurations) {
        this.topicConfigurations = null;
        this.topicConfigurations = new ArrayList(1);
        this.topicConfigurations.addAll(topicConfigurations);
    }

    public BucketNotificationConfiguration withTopicConfigurations(TopicConfiguration... topicConfigurations) {
        this.topicConfigurations.clear();
        for (Object add : topicConfigurations) {
            this.topicConfigurations.add(add);
        }
        return this;
    }

    public void setTopicConfigurations(Collection<TopicConfiguration> topicConfigurations) {
        this.topicConfigurations.clear();
        this.topicConfigurations.addAll(topicConfigurations);
    }

    public List<TopicConfiguration> getTopicConfigurations() {
        return this.topicConfigurations;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("TopicConfigurations: " + getTopicConfigurations());
        sb.append("}");
        return sb.toString();
    }
}
