package com.amazonaws.services.s3.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BucketTaggingConfiguration {
    private List<TagSet> tagSets;

    public BucketTaggingConfiguration() {
        this.tagSets = null;
        this.tagSets = new ArrayList(1);
    }

    public BucketTaggingConfiguration(Collection<TagSet> tagSets) {
        this.tagSets = null;
        this.tagSets = new ArrayList(1);
        this.tagSets.addAll(tagSets);
    }

    public BucketTaggingConfiguration withTagSets(TagSet... tagSets) {
        this.tagSets.clear();
        for (Object add : tagSets) {
            this.tagSets.add(add);
        }
        return this;
    }

    public void setTagSets(Collection<TagSet> tagSets) {
        this.tagSets.clear();
        this.tagSets.addAll(tagSets);
    }

    public List<TagSet> getAllTagSets() {
        return this.tagSets;
    }

    public TagSet getTagSet() {
        return (TagSet) this.tagSets.get(0);
    }

    public TagSet getTagSetAtIndex(int index) {
        return (TagSet) this.tagSets.get(index);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("TagSets: " + getAllTagSets());
        sb.append("}");
        return sb.toString();
    }
}
